package com.home.test.persist.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RedisHash {
    public static void main(String[] args) {

        //Connecting to Redis server on localhost
//        Jedis jedis = new Jedis("172.17.0.4", 6379);
        JedisCluster jedis = new JedisCluster(new HostAndPort(AppConfig.getClusterIp(), 6379));
        System.out.println("Connection to server sucessfully");

        jedis.hset("user#1", "name", "peter");
        jedis.hset("user#1", "job", "politician");

        Map<String, String> fields = jedis.hgetAll("user#1");
        String name = fields.get("name");
        String job = fields.get("job");

        System.out.println("Retrieved name: " + name + ", job = " + job);
    }
}
