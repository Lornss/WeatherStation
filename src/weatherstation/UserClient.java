package weatherstation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class UserClient {

    public static void main(String[] args) throws IOException {

        //Create socket and I/O streams
        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 8080);
        DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

        outputStream.writeUTF("client");

        //Constantly listen for incoming doubles
        while(true){
            double msg = inputStream.readDouble();
            System.out.println(msg);
        }



    }
}
