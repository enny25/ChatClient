/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chatclient;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 *
 * @author Lenovo
 */
public class Client {
    PrintWriter output;
    BufferedReader input;
    String username;

    public Client( BufferedReader input,PrintWriter output, String username) {
        this.input = input;
        this.output = output;
        this.username = username;
    }

    public PrintWriter getOutput() {
        return output;
    }

    public  BufferedReader getInput() {
        return input;
    }

    public String getUsername() {
        return username;
    }

    
}
