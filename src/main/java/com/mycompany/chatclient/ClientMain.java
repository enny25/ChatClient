/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocol.ProtocolString;


/**
 *
 * @author Lenovo
 */
public class ClientMain {
    static String ip = "localhost";
    static int port = 8080;
     static String inc = null;
     static String parts[];
     static String user= "Gary";
     
    public static void main(String[] args) {
        
        if (args.length == 2) {
            ip = args[0];
            port = Integer.parseInt(args[1]);
            
        }
        Socket clientSocket = null;
        
        
        try { 
            clientSocket = new Socket(ip,port);
        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter output=null;
        BufferedReader input = null;
        try {
            output = new PrintWriter(clientSocket.getOutputStream(),true);
            input= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       InputHandler inputThread = new InputHandler(input,output);
       new Thread(inputThread).start();
        
       
        }
      
        
       
       
    }
  
    

