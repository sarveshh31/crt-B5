// socket programming.......
// socket = communication end point

// socket act like a connection between client and server
// between client and server.......

// client --> socket -->server

// key concepts......
//ServerSocket : waits for communication
//Socket       : actual communication channel
//InputStream  : receives data
//OutputStream : sends data
//accept       : waits for clients


// Q1. Basic : chat message sender....(client ---> server)
// problem : customer sends message to customer support....
// solution : create,
//              1) a server that waits for the clients message...
//              2) a client that sends : "Hello Support team."

// used for: BufferedReader & InputStreamReader

//(Networking : Socket Communication)
// Used for Socket & ServerSocket

package com.example;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {

        try {
            // create server on port no: 1060
            // Internal working : java opens port 1060 and listen
            //                    for incoming client request.

            ServerSocket serverSocket = new ServerSocket(1060);

            System.out.println("Server waiting for client message...");

            //Most imp line..
            //server waits until client connects.
            //Internal working: program pauses here & it stays
            //                  blocked until client
            Socket socket = serverSocket.accept();

            //create input channel to receive message from client.
            //getInputStream --> gets raw data from client.
            //inputStreamReader --> converts byte data into readable characters.
            //BufferedReader --> reads text efficiently line by line
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            // reads one full line sent by client....
            String msg = br.readLine();

            System.out.println("Client Message: " + msg);

            // close connection
            br.close();
            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}