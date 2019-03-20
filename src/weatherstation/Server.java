package weatherstation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8080);

        while(true){
            Socket s = null;
            try{
                s = socket.accept();

                System.out.println("A client has connected");

                //Creating input and output streams
                DataInputStream inputStream = new DataInputStream(s.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());

                Thread t = newClientHandler(s, inputStream, outputStream);
                t.start();
            }

            catch(Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }


}
