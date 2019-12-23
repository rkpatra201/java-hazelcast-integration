package com.java.hc;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelConfig {
    private static Config getConfig() {
        com.hazelcast.config.Config config = new com.hazelcast.config.Config();
        config.setInstanceName("studio-instance-test");
        //config.getGroupConfig().setName("studio-group-test");
       // NetworkConfig network = config.getNetworkConfig();
      //  network.setPort(5701);
       // network.setPortAutoIncrement(false);
     //   JoinConfig join = network.getJoin();
      //  join.getMulticastConfig().setEnabled(true);
       // config.setNetworkConfig(network);
        return config;
    }

    public static HazelcastInstance getHazelCastInstance() {
        HazelcastInstance instance = Hazelcast.getOrCreateHazelcastInstance(getConfig());
        return instance;
    }
}