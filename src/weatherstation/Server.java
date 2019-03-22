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

                String received = inputStream.readUTF();

                switch (received){
                    case "client":
                        Thread tClient = new ClientHandler(s, inputStream, outputStream);
                        tClient.start();

                    case "station":
                        Thread tStation = new StationHandler(s, inputStream, outputStream);
                        tStation.start();

                    default:
                        outputStream.writeUTF("Invalid client");
                }

                if(received.equals("Client")){
                    Thread t = new ClientHandler(s, inputStream, outputStream);
                    t.start();
                }


            }

            catch(Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }


}
