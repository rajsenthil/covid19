package com.home.test.persist.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisSet {
    public static void main(String[] args) {

        //Connecting to Redis server on localhost
//        Jedis jedis = new Jedis("172.17.0.4", 6379);
        JedisCluster jedis = new JedisCluster(new HostAndPort(AppConfig.getClusterIp(), 6379));
        System.out.println("Connection to server sucessfully");

        //store data in redis list
        jedis.sadd("fruits", "apple");
        jedis.sadd("fruits", "orange");
        jedis.sadd("fruits", "apricot");
        // Get the stored data and print it
        Set<String> fruits  = jedis.smembers("fruits");

        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            System.out.println("Next fruit: " + iterator.next());
        }
    }
}
