/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chatclient;

import static com.mycompany.chatclient.ClientMain.port;
import static com.mycompany.chatclient.ClientMain.user;
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
public class InputHandler implements Runnable{
   BufferedReader input;
   PrintWriter output;
   String inc;
   String user = "gary";

    public InputHandler(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }
   
   
    
    
    public void receive(){
        
        
    }

    @Override
    public void run() {
        output.println(ProtocolString.LOGIN +user);
       try{
           while (( inc = input.readLine()) != null){
               if(inc.startsWith(ProtocolString.CLIENTLIST)){
                   ClientList(inc);
               }
               if(inc.startsWith(ProtocolString.MSGRES)){
                   MsgRes(inc);
               }
               
               
               
           }
       }
       catch(Exception ex){
           System.out.println("Something went Wrong in run");
       }
               
          
       
       
    }

    private void ClientList(String inc) {
        String[] clientSplit = inc.split(":");
        String clients = clientSplit[1];
        System.out.println(clients);
    }

    private void MsgRes(String inc) {
        String[] msgResSplit = inc.split(":");
        String sender = msgResSplit[1];
        String msg = msgResSplit[2];
        System.out.println("Sender "+ sender+" msg " + msg);
    }
    
}
