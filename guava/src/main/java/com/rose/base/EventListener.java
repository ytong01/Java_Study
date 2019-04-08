package com.rose.base;

import com.google.common.eventbus.Subscribe;

public class EventListener {

    private int lastMessage;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("receive message == " + lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}
