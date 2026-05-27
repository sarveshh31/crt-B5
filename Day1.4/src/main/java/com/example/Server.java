package com.example;

import java.io.*;
import java.net.*;

class ClientHandler implements Runnable {

    Socket socket;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            int num1 = Integer.parseInt(br.readLine());
            int num2 = Integer.parseInt(br.readLine());
            synchronized (Server.class) {

                int sum = num1 + num2;

                System.out.println("Number 1 : " + num1);
                System.out.println("Number 2 : " + num2);
                System.out.println("Addition Result : " + sum);

                PrintWriter pw = new PrintWriter(
                        socket.getOutputStream(), true
                );

                pw.println("Addition Result = " + sum);

                pw.close();
            }

            // close
            br.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

// Calculator Server
public class Server {

    public static void main(String[] args) throws Exception {

        // create server socket
        ServerSocket serverSocket =
                new ServerSocket(8000);

        System.out.println("Calculator Server Started...");

        while (true) {

            // accept multiple clients
            Socket socket = serverSocket.accept();

            // create thread
            ClientHandler client =
                    new ClientHandler(socket);

            Thread t = new Thread(client);

            t.start();
        }
    }
}