/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverudpecho;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc15
 */
public class UDPEcho {

    private DatagramSocket socket;

    public UDPEcho(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void run() {
        DatagramPacket answer;
        byte[] buffer = new byte[8192];
        // creo un un datagramma UDP a partire da buffer
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        for (;;) {
            try {
                socket.receive(request);
                // prendo i dati ricevuti e costruisco la risposta
                answer = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                socket.send(answer);
            } catch (IOException ex) {
                Logger.getLogger(UDPEcho.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
