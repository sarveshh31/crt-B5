package com.example;

import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        try {
            // client connect to server at 1060
            Socket socket = new Socket("localhost", 1060);

            //creates output channel to send data to server.
            //true : enables automatic flushing &
            //       message sends immediately.
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            pw.println("Hello Support team.");

            System.out.println("Message Sent!");

            // client disconnects after sending message
            pw.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        // internal data flow....
        // client --> OutputStream --> Networks --> server InputStream
    }
}