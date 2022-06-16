package com.example.javaproject;

import android.annotation.SuppressLint;
import android.widget.TextView;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private String password;
    private String type;

    public static String messageFromChat;
    public static String messageToSend;
    public static boolean sendMsg;
    public Client(Socket socket, String username, String password, String type){
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
            this.password = password;
            this.type = type;
        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    bufferedWriter.write(username);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    bufferedWriter.write(password);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    sendMsg = false;
                    while (socket.isConnected()) {
                        while (!sendMsg) ; // wait here
                        bufferedWriter.write(username + " : " + messageToSend);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        sendMsg = false;
                    }
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();
    }


    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(socket.isConnected()){
                    try {
                        messageFromChat = bufferedReader.readLine();
                        if (messageFromChat == null){
                            closeEverything(socket, bufferedReader, bufferedWriter);
                            return;
                        }
                    } catch (IOException e){
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try {
            if(bufferedReader != null) {
                bufferedReader.close();
            }
            if(bufferedWriter != null) {
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

