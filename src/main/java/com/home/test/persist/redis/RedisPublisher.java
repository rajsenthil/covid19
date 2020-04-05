package com.home.test.persist.redis;

import redis.clients.jedis.*;

import java.util.Set;

public class RedisPublisher {
    public static void main(String[] args) {

        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis("172.17.0.9", 6379);
//        JedisCluster jedis = new JedisCluster(new HostAndPort(AppConfig.getClusterIp(), 6379));
        System.out.println("Connection to server sucessfully");

        jedis.publish("channel", "test message");
    }
}
