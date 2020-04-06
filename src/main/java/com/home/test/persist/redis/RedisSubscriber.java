package com.home.test.persist.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPubSub;

public class RedisSubscriber {
    public static void main(String[] args) {

        //Connecting to Redis server on localhost
//        Jedis jedis = new Jedis("172.17.0.9", 6379);
        JedisCluster jedis = new JedisCluster(new HostAndPort(AppConfig.getClusterIp(), 6379));
        System.out.println("Connection to server sucessfully");

        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("Channel: " + channel + ", Message Received: " + message);
            }
        }, "channel");
    }
}
