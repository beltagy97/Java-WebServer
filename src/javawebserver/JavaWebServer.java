/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javawebserver;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaWebServer {

    private static final int port = 4000;
    private static final String IndexFile = "Index.html";
    private static final String FileNotFound = "404.html";

//    response message hander
    public void SendResponse(BufferedReader input,PrintWriter out ,BufferedOutputStream dataOut ,boolean isValidMethod) {

    }

    public static void main(String[] args) throws IOException {

        try {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(100000);
            System.out.println("Web Server has started successfully");

            while (true) {
                
                    Socket socket = server.accept();
                    System.out.println("connection established");
                

            }

        } catch (IOException e) {
            System.out.println("Server connection error : " + e);
        }

    }

}
