package weatherstation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ClientHandler implements Runnable{

    final DataInputStream inputStream;
    final DataOutputStream outputStream;
    final Socket socket;


    public ClientHandler(Socket s, DataInputStream input, DataOutputStream output){
        this.inputStream = input;
        this.outputStream = output;
        this.socket = s;
    }


    @Override
    public void run(){


        try {
            outputStream.writeUTF("valid");
            sendData();

        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getStationData() throws IOException {
        for(StationHandler i: Server.Stations){
            double temp = i.getTemperature();
            outputStream.writeDouble(temp);
        }

    }

    //Temp function to keep sending station data every 5 seconds, this should be replaced
    private void sendData() throws InterruptedException, IOException {
        while(true){
            getStationData();
            TimeUnit.SECONDS.sleep(5);
        }
    }

}
