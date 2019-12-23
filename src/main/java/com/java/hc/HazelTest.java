package com.java.hc;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.Map;

public class HazelTest {
    public static void main(String[] args) {

        String type = System.getProperty("action.type");
        System.out.println("**********starting hazelcast ******");
        if (type.equalsIgnoreCase("p")) {
            producer();
        } else {
            consumer();
        }
    }

    public static void consumer() {
        HazelcastInstance instance = HazelConfig.getHazelCastInstance();
        Map<String, String> map = instance.getMap("dataMap");
        int i = 0;
        while (true) {
            System.out.println(map.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void producer() {
        HazelcastInstance instance = HazelConfig.getHazelCastInstance();
        Map<String, String> map = instance.getMap("dataMap");
        int i = 0;
        while (true) {
            i++;
            ((IMap<String, String>) map).put("k" + i, "v");
            System.out.println("producing");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
