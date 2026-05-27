package com.example;

//concepts used
//1) MultiThreading
//2) Synchronization

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 7000);
        PrintWriter pw = new PrintWriter(
                socket.getOutputStream(), true
        );

        pw.println("500");

        System.out.println("Deposit Sent!");
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        String response = br.readLine();

        System.out.println("Server Response: " + response);

        br.close();
        pw.close();
        socket.close();
    }
}