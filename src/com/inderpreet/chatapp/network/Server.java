package com.inderpreet.chatapp.network;

import com.inderpreet.chatapp.utils.ConfigReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    ServerSocket serverSocket;
    ArrayList<ServerWorker> workers = new ArrayList<>();
    public Server() throws IOException {
        int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
        serverSocket = new ServerSocket(PORT);
        handleClientRequest();
    }
    // Multiple Client
    public void handleClientRequest() throws IOException {
        while (true){
            Socket clientSocket = serverSocket.accept();
            ServerWorker serverWorker = new ServerWorker(clientSocket,this);
            workers.add(serverWorker);
            serverWorker.start();
        }
    }
}