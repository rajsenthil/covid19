package com.home.test.persist.redis;

import redis.clients.jedis.*;

public class RedisStringJava {
    public static void main(String[] args) {
        //Connecting to Redis server on localhost
//        Jedis jedis = new Jedis("10.107.43.112");
        JedisPool pool = new JedisPool(new JedisPoolConfig(), AppConfig.getClusterIp());
        Jedis jedis = pool.getResource();
        jedis.auth("password");

        System.out.println("Connection to server sucessfully");
        //set the data in redis string
        jedis.set("tutorial-name", "Redis tutorial");
        // Get the stored data and print it
        System.out.println("Stored string in redis:: "+ jedis.get("tutorial-name"));
    }
}
