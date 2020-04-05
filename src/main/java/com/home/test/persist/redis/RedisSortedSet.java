package com.home.test.persist.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.Map;

public class RedisSortedSet {

    public static void main(String[] args) {

        //Connecting to Redis server on localhost
//        Jedis jedis = new Jedis("172.17.0.4", 6379);
        JedisCluster jedis = new JedisCluster(new HostAndPort(AppConfig.getClusterIp(), 6379));
        System.out.println("Connection to server sucessfully");

        Map<String, Double> scores = new HashMap<>();

        scores.put("PlayerOne", 33000.0);
        scores.put("PlayerTwo", 1500.0);
        scores.put("PlayerThree", 8200.0);
        String key = "ranking";

        scores.entrySet().forEach(playerScore -> {
            jedis.zadd(key, playerScore.getValue(), playerScore.getKey());
        });

//        String player = jedis.zrevrange("key", 0, 1).iterator().next();
//        System.out.println("Player rank#1: " + player);
        long rank = jedis.zrevrank(key, "PlayerOne");
        System.out.println("Rank of PlayerOne: "+rank);
    }
}
