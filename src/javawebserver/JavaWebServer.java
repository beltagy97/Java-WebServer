/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javawebserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class JavaWebServer {
    
    private static final int port = 4000;
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("Web Server has started successfully");
        
        while(true)
        {
            Socket socket = server.accept();
            System.out.println("connection established");
            
        }
        
    }
    
}
