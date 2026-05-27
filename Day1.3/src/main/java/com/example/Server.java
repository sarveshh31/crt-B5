package com.example;
// Q3. Difficult : Multi-Client Bank Transaction.
//     Problem : Multiple ATM users access Bank Transaction server.
//     Solution : creates,
//                  * server handles multiple clients using threads.
//                  * each client sends deposit amount.
//                  * server updates balance.

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

            int depositAmount = Integer.parseInt(br.readLine());

            synchronized (Server.class) {

                Server.balance =
                        Server.balance + depositAmount;

                System.out.println("Deposit Received: " + depositAmount);

                System.out.println("Updated Balance: "
                        + Server.balance);
            }

            PrintWriter pw = new PrintWriter(
                    socket.getOutputStream(), true
            );

            pw.println("Deposit Successful. Balance = "
                    + Server.balance);

            br.close();
            pw.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class Server {

    static int balance = 1000;

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(7000);

        System.out.println("Bank Server Started...");

        while (true) {

            Socket socket = serverSocket.accept();
            ClientHandler client =
                    new ClientHandler(socket);

            Thread t = new Thread(client);

            t.start();
        }
    }
}