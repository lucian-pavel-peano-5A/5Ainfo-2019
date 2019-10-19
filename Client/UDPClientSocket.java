/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 *
 * @author pc15
 */
public class UDPClientSocket {

    private DatagramSocket socket;

    public UDPClientSocket() throws SocketException {
        socket = new DatagramSocket();
        socket.setSoTimeout(1000);
    }

    public void close_socket() {
        socket.close();
    }

    public String sendAndRecive(String request, String host, int port) throws UnknownHostException, IOException {
        byte[] buffer;
        DatagramPacket datagram;
        String answer;
        // indirizzo iP del destinatario del nostro pacchetto
        InetAddress address = InetAddress.getByName(host);

        //verifico che il socket non sia chiuso
        if (socket.isClosed()) {
            throw new IOException();
        }
        //Trasformo in array di byte la stringa che voglio inviare
        buffer = request.getBytes("UTF-8");

        // Costruisco il datagram di richiesta
        datagram = new DatagramPacket(buffer, buffer.length, address, port);

        // spedisco il datagram
        socket.send(datagram);

        // Attesa della ricecione del datagram
        socket.receive(datagram);

        if (datagram.getAddress().equals(address) && datagram.getPort() == port) {
            answer = new String(datagram.getData(), 0, datagram.getLength(), "ISO-8859-1");
        } else {
            throw new SocketTimeoutException();
        }
        return answer;
    }

}
