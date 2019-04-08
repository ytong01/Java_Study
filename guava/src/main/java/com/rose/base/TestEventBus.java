package com.rose.base;

import com.google.common.eventbus.EventBus;

public class TestEventBus {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        EventListener listener = new EventListener();
        eventBus.register(listener);

        eventBus.post(new TestEvent(100));
        eventBus.post(new TestEvent(500));
    }

}
