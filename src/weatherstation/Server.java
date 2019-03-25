package weatherstation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class Server {

    static ArrayList<ClientHandler> Clients = new ArrayList<ClientHandler>();
    static ArrayList<StationHandler> Stations = new ArrayList<StationHandler>();

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
                        Clients.add((ClientHandler) tClient);
                        tClient.start();
                        break;

                    case "station":
                        Thread tStation = new StationHandler(s, inputStream, outputStream);
                        Stations.add((StationHandler) tStation);
                        tStation.start();
                        break;

                    default:
                        outputStream.writeUTF("Invalid Client");
                        s.close();
                }





            }

            catch(Exception e){
                s.close();
            }
        }
    }


}
