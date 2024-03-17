package com.inderpreet.chatapp.network;

import com.inderpreet.chatapp.utils.ConfigReader;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    Socket socket;
    OutputStream out;
    InputStream in;
    ClientWorker worker;
    JTextArea textArea;
    public Client(JTextArea textArea) throws IOException {
        int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
            socket = new Socket(ConfigReader.getValue("SERVER_IP"),PORT);
            out = socket.getOutputStream();
            in = socket.getInputStream();
            this.textArea = textArea;
            readMessage();
    }
    public void sendMessage(String message) throws IOException {
        message = message+ "\n";
        out.write(message.getBytes());
    }
    public void readMessage(){
        worker = new ClientWorker(in,textArea);
        worker.start();
    }
}