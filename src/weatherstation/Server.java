package weatherstation;

import com.sun.security.ntlm.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server implements Runnable{

    static ArrayList<ClientHandler> Clients = new ArrayList<>();
    static ArrayList<StationHandler> Stations = new ArrayList<>();
    
    @Override
    public void run(){
        try {
            Server.mainServer();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public static void mainServer() throws IOException {
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
                        ClientHandler client = new ClientHandler(s, inputStream, outputStream);
                        Thread tClient = new Thread(client);
                        Clients.add(client);
                        tClient.start();
                        break;

                    case "station":
                        StationHandler station = new StationHandler(s, inputStream, outputStream);
                        Thread tStation = new Thread (station);
                        Stations.add(station);
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
