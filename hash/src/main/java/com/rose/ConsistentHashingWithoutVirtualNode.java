package com.rose;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashingWithoutVirtualNode {

    private static String[] servers = {"192.168.0.0:2181", "192.168.0.1:2181", "192.168.0.2:2181", "192.168.0.3:2181", "192.168.0.4:2181"};

    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();
    static{
        for (int i = 0; i < servers.length; i++) {
            int hash = getHash(servers[i]);
            System.out.println("[" + servers[i] + "]加入集合中，其hash值为" + hash);
            sortedMap.put(hash, servers[i]);
        }
    }

    private static int getHash(String str) {

        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

    private static String getServer(String key) {

        int hash = getHash(key);
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        if (subMap.isEmpty()) {
            Integer i = sortedMap.firstKey();
            return sortedMap.get(i);
        } else {
            Integer i = subMap.firstKey();
            return subMap.get(i);
        }
    }

    public static void main(String[] args) {
        String[] keys = {"太阳", "月亮", "星星"};
        for (int i = 0; i < keys.length; i++) {
            System.out.println("[" + keys[i] + "的hash值为" + getHash(keys[i])
                + ", 被路由到节点[" + getServer(keys[i]) + "]");
        }
    }
}
