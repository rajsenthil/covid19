package com.home.test.persist.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Map;

public class RedisClister {
    public static void main(String[] args) {
        JedisCluster jedisCluster = new JedisCluster(new HostAndPort(AppConfig.getClusterIp(), 6379));
        System.out.println("Connection to server sucessfully");

        for (Map.Entry<String, JedisPool> node : jedisCluster.getClusterNodes().entrySet()) {
            try (Jedis jedis = node.getValue().getResource()) {
                jedis.info("memory");
                //Node is OK
            } catch (JedisConnectionException jce) {
                //Node FAILS
            }
        }
    }
}
