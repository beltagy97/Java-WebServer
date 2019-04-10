/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javawebserver;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaWebServer implements Runnable{

    private static final int port = 4000;
    private static final String IndexFile = "Index.html";
    private static final String FileNotFound = "404.html";

    private Socket client;

    public JavaWebServer(Socket skt) {
        client = skt;
    }

//    response message hander
    public void SendResponse(BufferedReader input, PrintWriter out, BufferedOutputStream dataOut, boolean isValidMethod) {

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
        
        System.out.println("Just connected to " + client.getRemoteSocketAddress());
        DataInputStream in;
        try {
            in = new DataInputStream(client.getInputStream());
            System.out.println(in.readUTF());
        } catch (IOException ex) {
            Logger.getLogger(JavaWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
