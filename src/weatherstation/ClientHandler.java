package weatherstation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread{

    final DataInputStream inputStream;
    final DataOutputStream outputStream;
    final Socket socket;


    public ClientHandler(Socket s, DataInputStream input, DataOutputStream output){
        this.inputStream = input;
        this.outputStream = output;
        this.socket = s;
    }

    public  void sendTemps() throws IOException {
        for(StationHandler i: Server.Stations){
            outputStream.writeDouble(i.getTemperature());
        }
    }

    @Override
    public void run(){


            try {
                outputStream.writeUTF("Valid Client");
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            while(true) {
                try {
                    sendTemps();
                    Thread.sleep(5000);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }







    }
}
