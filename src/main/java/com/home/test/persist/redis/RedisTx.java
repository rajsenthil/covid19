package com.home.test.persist.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Transaction;

import java.util.HashMap;
import java.util.Map;

public class RedisTx {
    public static void main(String[] args) {

        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis("172.17.0.9", 6379);
//        JedisCluster jedis = new JedisCluster(new HostAndPort(AppConfig.getClusterIp(), 6379));
        System.out.println("Connection to server sucessfully");

        String friendsPrefix = "friends#";
        String userOneId = "4352523";
        String userTwoId = "5552321";

        Transaction t = jedis.multi();
        try {
            t.sadd(friendsPrefix + userOneId, userTwoId);
            t.sadd(friendsPrefix + userTwoId, userOneId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        t.exec();
    }
}
