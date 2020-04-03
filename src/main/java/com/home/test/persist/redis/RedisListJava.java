package com.home.test.persist.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.List;

public class RedisListJava {
    public static void main(String[] args) {

        //Connecting to Redis server on localhost
//        Jedis jedis = new Jedis("172.17.0.4", 6379);
        JedisCluster jedis = new JedisCluster(new HostAndPort("172.17.0.4", 6379));
        System.out.println("Connection to server sucessfully");

        //store data in redis list
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        // Get the stored data and print it
        List<String> list = jedis.lrange("tutorial-list", 0 ,5);

        for(int i = 0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list.get(i));
        }
    }
}