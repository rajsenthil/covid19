package com.home.test.persist.redis;

import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisJava {
    public static void main(String[] args) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), AppConfig.getClusterIp());
        Jedis jedis = pool.getResource();
        jedis.auth("password");

        String hi = jedis.get("hi");

//        jedis.set("2020-04-08", "2020-04-08 => April 08th, 2020");

        String someDate = jedis.get("2020-04-08");

        String test1 = jedis.get("tutorial-name");
        System.out.println("hi= " + hi);


        System.out.println("Some date: " + someDate);
        System.out.println("Test1: " + test1);

        List<String> list = jedis.lrange("tutorial-list", 0 ,5);

        for(int i = 0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list.get(i));
        }

        Set<String> fruits  = jedis.smembers("fruits");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            System.out.println("Next fruit: " + iterator.next());
        }

        System.out.println("Hash key map");
        Map<String, String> fields = jedis.hgetAll("user#1");
        String name = fields.get("name");
        String job = fields.get("job");

        System.out.println("Retrieved name: " + name + ", job = " + job);

        System.out.println("Getting user object");
        Map<String, String> users = jedis.hgetAll("users");
        users.forEach((k, v) -> System.out.println("Key: " + k + ", value: "+ v));

    }
}
