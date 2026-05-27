package com.example;

//Q2. Moderate : Online Food Order System
//      Problem : customer sends food order to restaurant server.
//      solution : create
//              * Client sends food name to server.
//              * Server responds with confirmation.

// Concept used :
// Two-way Communication
// Request-Response model

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {

        // create server socket
        ServerSocket serverSocket = new ServerSocket(1070);

        System.out.println("Restaurant Server Waiting For Orders...");

        // accept client connection
        Socket socket = serverSocket.accept();

        // read food order from client
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        String foodOrder = br.readLine();

        System.out.println("Customer Ordered: " + foodOrder);

        // send confirmation to client
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        pw.println("Order Confirmed : " + foodOrder);

        // close
        br.close();
        pw.close();
        socket.close();
        serverSocket.close();
    }
}