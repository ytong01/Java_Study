package com.rose;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class WeakCacheTest {

    private static Map<String, WeakEntry> cache = new HashMap<>();
    private static ReferenceQueue<? super Object> garbageCollectionQueue = new ReferenceQueue<>();

    public static void main(String[] args) throws InterruptedException {

        User rose = new User("rose", 27);
        cache.put("rose", new WeakEntry("rose", rose, garbageCollectionQueue));
        cache.put("tony", new WeakEntry("tony", new User("tony", 26), garbageCollectionQueue));

        System.out.println(JSONObject.toJSONString(cache));

        System.gc();
        TimeUnit.SECONDS.sleep(2);
        WeakEntry entry;

        while ((entry = (WeakEntry) garbageCollectionQueue.poll()) != null) {
            for (Iterator<Map.Entry<String, WeakEntry>> iter = cache.entrySet().iterator(); iter.hasNext();) {
                Map.Entry<String, WeakEntry> current = iter.next();
                if (current.getValue() == entry) {
                    iter.remove();
                }
            }
        }
        System.out.println(JSONObject.toJSONString(cache));
    }

    private static class WeakEntry extends WeakReference<Object> {

        private Object key;
        public WeakEntry(Object key, Object value, ReferenceQueue<? super Object> garbageCollectionQueue) {
            super(value, garbageCollectionQueue);
            this.key = key;
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }

    private static class User implements Serializable {

        private String username;
        private Integer age;

        public User(String username, Integer age) {
            this.username = username;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
