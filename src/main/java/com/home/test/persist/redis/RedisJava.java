package com.home.test.persist.redis;

import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Map;

public class RedisJava {
    public static void main(String[] args) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), AppConfig.getClusterIp());
        Jedis jedis = pool.getResource();
        jedis.auth("password");

        String hi = jedis.get("hi");

//        jedis.set("2020-04-08", "2020-04-08 => April 08th, 2020");

        String someDate = jedis.get("2020-04-08");
        System.out.println("hi= " + hi);

        System.out.println("Some date: " + someDate);
    }
}
