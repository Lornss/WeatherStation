package weatherstation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class StationHandler extends Thread{

    final DataInputStream inputStream;
    final DataOutputStream outputStream;
    final Socket socket;

    public StationHandler(Socket s, DataInputStream input, DataOutputStream output){
        this.inputStream = input;
        this.outputStream = output;
        this.socket = s;
    }

    @Override
    public void run(){

    }

}
