package com.java.hc;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;
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
        ILock lock = getFencedLock(instance);
        lock.lock();
        while (true) {
            System.out.println(map.size());
            i++;
            if(i==9)
            {
                lock.unlock();
                System.out.println("releasing consumer lock");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static ILock getFencedLock(HazelcastInstance hazelcastInstance)
    {
        ILock fencedLock = hazelcastInstance.getLock("lock-1");
        return fencedLock;
    }
    public static void producer() {
        HazelcastInstance instance = HazelConfig.getHazelCastInstance();
        Map<String, String> map = instance.getMap("dataMap");
        int i = 0;
        ILock lock = getFencedLock(instance);
        lock.lock();
        while (true) {
            i++;
            ((IMap<String, String>) map).put("k" + i, "v");
            System.out.println("producing");

            if(i==10)
            {
                System.out.println("releasing producer lock");
                lock.unlock();
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
