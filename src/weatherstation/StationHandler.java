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

        while(true) {
            try {
                Temperature = inputStream.readDouble();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
