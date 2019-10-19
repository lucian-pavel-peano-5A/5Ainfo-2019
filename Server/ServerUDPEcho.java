/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverudpecho;

import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc15
 */
public class ServerUDPEcho {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            UDPEcho echoServer= new UDPEcho(7);
            echoServer.run();
        } catch (SocketException ex) {
            Logger.getLogger(ServerUDPEcho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
