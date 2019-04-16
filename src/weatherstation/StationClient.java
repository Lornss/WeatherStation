package weatherstation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StationClient extends Thread{
    
        @Override
    public void run(){
            try {
                StationClient.mainStation();
            } catch (IOException ex) {
                Logger.getLogger(StationClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(StationClient.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public static void mainStation() throws IOException, InterruptedException {

        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 8080);
        DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

        //Send client type
        //Add in authentication here later
        outputStream.writeUTF("station");

        while(true){
            outputStream.writeDouble(genData());
            TimeUnit.SECONDS.sleep(5);
        }


    }

    private static double genData(){
            double data = Math.random() * 50;
            return data;
    }
}
