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
import java.util.List;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocol.ProtocolString;

/**
 *
 * @author Lenovo
 */
public class ClientForm extends java.util.Observable {
    Socket clientSocket = null;
    PrintWriter output = null;
    BufferedReader input = null;
    Observer o;
    
    public void Connect(String ip,int port,String username){
        try { 
            clientSocket = new Socket(ip,port);
             output = new PrintWriter(clientSocket.getOutputStream(),true);
            input= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
           
    public void Disconnect () throws IOException{
        input.close();
        output.close();
        clientSocket.close();
        
    }

    void sendMsg(String[] onlineUsers, String msg) {
        String users= "";
        for (String onlineUser : onlineUsers) {
             users =  users +onlineUser +",";
            
        }
        output.println(ProtocolString.MSG+users+":"+msg);
    }
    public void ClientList(String inc) {
        String[] clientSplit = inc.split(":");
        String clients = clientSplit[1].toString();
        System.out.println(clients);
        
        System.out.println("from clientlist method");
        addObserver(o);
        setChanged();
        notifyObservers(inc);
    }

    public void MsgRes(String inc) {
        String[] msgResSplit = inc.split(":");
        String sender = msgResSplit[1];
        String msg = msgResSplit[1]+msgResSplit[2];
        System.out.println("Sender "+ sender+" msg " + msg);
        System.out.println("from msgres method");
        addObserver(o);
        setChanged();
        notifyObservers(msg);
    }
    
    public void startIncThread( ChatGui cGUI){
        this.o=cGUI;
        InputHandler inputThread = new InputHandler();
        new Thread(inputThread).start();
        
    }
    public class InputHandler implements Runnable{
   
   
   
    

    @Override
    public void run() {
         String inc;
       try{
           while (( inc = input.readLine()) != null){
               System.out.println(inc);
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
    }
    
}
