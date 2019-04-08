package com.rose.socket;

import com.google.common.eventbus.EventBus;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EventBusChat {

    public static void main(String[] args) {

        EventBus eventBus = new EventBus();
        try (ServerSocket serverSocket = new ServerSocket(11122)) {

            while (true) {
                Socket socket = serverSocket.accept();

                UserThread userThread = new UserThread(socket, eventBus);
                eventBus.register(userThread);
                userThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
