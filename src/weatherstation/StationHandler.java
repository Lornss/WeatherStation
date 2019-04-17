package weatherstation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class StationHandler implements Runnable{

    final DataInputStream inputStream;
    final DataOutputStream outputStream;
    final Socket socket;
    double Temperature;

    public StationHandler(Socket s, DataInputStream input, DataOutputStream output){
        this.inputStream = input;
        this.outputStream = output;
        this.socket = s;
    }

    public double getTemperature() {
        return Temperature;
    }

    @Override
    public void run(){


        try {
            outputStream.writeUTF("Valid Client");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        while(i < 4) {
            try {
                Temperature = inputStream.readDouble();
                i++;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        removeHandler();

    }

    //Generates an error in console but still removes the handler
    //This needs to be fixed at some point
    synchronized private void removeHandler(){

        for(StationHandler i: Server.Stations){
            if(i.equals(this)) {
                Server.Stations.remove(this);
            }
        }
    }
}
