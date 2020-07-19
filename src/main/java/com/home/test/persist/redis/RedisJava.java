package com.home.test.persist.redis;

import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.*;

public class RedisJava {

    private static final String MASTER_NAME = "mymaster";
    private static final String PASSWORD = "ENC(wcZnZ23gsuBHJGpjpPMPKUJHCaEqc26BxN+/+csntDsRGrI3ANTH0LsF+n7fM6Cr)";
    private static final Set sentinels;
    static {
        sentinels = new HashSet();
        sentinels.add("127.0.0.1:30300");
    }

    public static void main(String[] args) {
        
        Jedis jedis = null;

        try {
            JedisSentinelPool pool = new JedisSentinelPool(MASTER_NAME, sentinels);
            System.out.println("Fetching connection from pool.");
            jedis = pool.getResource();
            jedis.auth("ENC(wcZnZ23gsuBHJGpjpPMPKUJHCaEqc26BxN+/+csntDsRGrI3ANTH0LsF+n7fM6Cr)");
            String hi = jedis.get("hi");

//        jedis.set("2020-04-08", "2020-04-08 => April 08th, 2020");

            String someDate = jedis.get("2020-04-08");

            String test1 = jedis.get("tutorial-name");
            System.out.println("hi= " + hi);


            System.out.println("Some date: " + someDate);
            System.out.println("Test1: " + test1);

            List<String> list = jedis.lrange("tutorial-list", 0, 5);

            for (int i = 0; i < list.size(); i++) {
                System.out.println("Stored string in redis:: " + list.get(i));
            }

            Set<String> fruits = jedis.smembers("fruits");
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
            users.forEach((k, v) -> System.out.println("Key: " + k + ", value: " + v));

            //For catalog, may or may not work
            System.out.println("Catalog Hash key map");
            Map<String, String> catalogs = jedis.hgetAll("catalog");
            catalogs.forEach((key, value) -> {
                System.out.printf("key: %s, value: %s", key, value);
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
