package com.rose.socket;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.io.*;
import java.net.Socket;

public class UserThread extends Thread {

    private Socket connection;
    private EventBus channel;
    private BufferedWriter writer;
    private BufferedReader reader;

    @Subscribe
    public void listen(String text) {
        System.out.println("receive message == " + text);
    }

    public UserThread(Socket connetion, EventBus channel) {

        this.connection = connetion;
        this.channel = channel;
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(connetion.getOutputStream()));
            this.reader = new BufferedReader(new InputStreamReader(connetion.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try{
            String input;
            while ((input = reader.readLine()) != null) {

                channel.post(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        channel.unregister(this);
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader = null;
        writer = null;
    }
}
