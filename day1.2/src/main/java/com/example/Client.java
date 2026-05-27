package com.example;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {

        // connect to server
        Socket socket = new Socket("localhost", 1070);

        // send food order
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        pw.println("Burger");

        System.out.println("Food Order Sent!");

        // receive confirmation from server
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        String response = br.readLine();

        System.out.println("Server Response: " + response);

        // close
        br.close();
        pw.close();
        socket.close();
    }
}
