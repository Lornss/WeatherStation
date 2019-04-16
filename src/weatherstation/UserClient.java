package weatherstation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserClient extends Thread{
    
        @Override
    public void run(){
            try {
                UserClient.mainClient();
            } catch (IOException ex) {
                Logger.getLogger(UserClient.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public static void mainClient() throws IOException {

        //Create socket and I/O streams
        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 8080);
        DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

        outputStream.writeUTF("client");
        if(!inputStream.readUTF().equals("valid")){
            clientSocket.close();
        }

        //Constantly listen for incoming doubles
        while(true){
            double msg = inputStream.readDouble();
            System.out.println(msg);
        }



    }
}
