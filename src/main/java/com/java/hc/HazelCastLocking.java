package com.java.hc;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;

public class HazelCastLocking {
    public static void main(String[] args) {
        HazelcastInstance instance = HazelConfig.getHazelCastInstance();
        ILock lock = instance.getLock("my-lock");
        lock.lock();
        int i =0;
        while(true)
        {
            System.out.println("printing");
            if(i==1000)
            {
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock.unlock();
    }
}
