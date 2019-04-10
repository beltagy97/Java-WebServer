/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javawebserver;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaWebServer implements Runnable {

    private static final int port = 4000;
    private static final String IndexFile = "INDEX.HTML";
    private static final String FileNotFound = "404.HTML";
    private Socket client;

    public JavaWebServer(Socket skt) {
        client = skt;
    }

    //validate header
    public boolean isValidHeader(String header) {
        header = header.toUpperCase();
        return header.contains("GET") || header.contains("POST");
    }

    public static void main(String[] args) throws IOException {

        try {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(100000);
            System.out.println("Web Server has started successfully");

            while (true) {

                JavaWebServer socket = new JavaWebServer(server.accept());
                Thread clientRequest = new Thread(socket);
                clientRequest.start();
            }

        } catch (IOException e) {
            System.out.println("Server connection error : " + e);
        }

    }

    /**
     *
     * @throws IOException
     */
    @Override
    public void run() {

        System.out.println("Connection established with " + client.getRemoteSocketAddress());
        DataInputStream in;
        DataOutputStream out;
        BufferedOutputStream dataOut;

        try {

            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            dataOut = new BufferedOutputStream(client.getOutputStream());

            String header = in.readUTF();

//            check if valid request or a bad request
            boolean validHeader = isValidHeader(header);
            System.out.println(header);

            if (validHeader) {
                out.writeUTF("HTTP/1.0 OK");
                out.flush();
            } else {
                out.writeUTF("404 Bad Request");
                out.flush();

            }

          
           
           
        } catch (IOException ex) {
            Logger.getLogger(JavaWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
