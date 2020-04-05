package com.home.test.persist.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

public class RedisConnectPool {

    private JedisPool jedisPool;

    private void initialize() {
        System.out.println("Initializing the pool");
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        jedisPool = new JedisPool(poolConfig, AppConfig.getClusterIp());
        System.out.println("Pool initialized");
    }

    public static void main(String[] args) {
        RedisConnectPool app = new RedisConnectPool();
        app.initialize();

        try (Jedis jedis = app.jedisPool.getResource()) {
            // do operations with jedis resource
            System.out.println(app.jedisPool.getResource().clusterInfo());
            System.out.println("Success");
        } catch (Exception ex) {
            ex.printStackTrace();
        }



    }

}
