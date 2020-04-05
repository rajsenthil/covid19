package com.home.test.persist.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Map;

public class RedisJava {
    public static void main(String[] args) {
        System.out.println("Starting...");
        System.out.println(AppConfig.getClusterIp());
        //Connecting to Redis server on localhost
        JedisCluster jedisCluster = new JedisCluster(new HostAndPort(AppConfig.getClusterIp(), 6379));
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not
        for (Map.Entry<String, JedisPool> node : jedisCluster.getClusterNodes().entrySet()) {
            try (Jedis jedis = node.getValue().getResource()) {
//                jedis.info("memory");
                System.out.println("Server is running: " + jedis.ping() + ", with IP addrewss: " + jedis.info(""));
                //Node is OK
            } catch (JedisConnectionException jce) {
                //Node FAILS
            }
        }
    }
}
