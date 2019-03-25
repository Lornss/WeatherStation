/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation;

/**
 *
 * @author N0756303
 */
public class WeatherStation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread tServer = new Thread(new Server());
        Thread tClient = new Thread(new UserClient());
        Thread tStation = new Thread(new StationClient());
        
        tServer.start();
        tClient.start();
        tStation.start();
        
       
    }
    
}
