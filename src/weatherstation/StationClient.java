package weatherstation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class StationClient {

    public static void main(String args[]) throws IOException, InterruptedException {

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
        return (Math.random() * 50);
    }
}
