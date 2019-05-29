package com.rose;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class WeakHashMapTest {

    public static void main(String[] args) throws InterruptedException {
        WeakHashMap<User, String> map = new WeakHashMap<>();
        map.put(new User("rose", 27), "rose");
        map.put(new User("tony", 27), "tony");
        System.gc();
        TimeUnit.SECONDS.sleep(2);

        System.out.println(map.size());
    }
}
