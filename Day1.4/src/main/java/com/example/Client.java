package com.example;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws Exception {

        Socket socket =
                new Socket("localhost", 8000);

        PrintWriter pw = new PrintWriter(
                socket.getOutputStream(), true
        );

        pw.println("10");
        pw.println("20");

        System.out.println("Numbers Sent!");

        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        String result = br.readLine();

        System.out.println("Server Response : " + result);

        br.close();
        pw.close();
        socket.close();
    }
}