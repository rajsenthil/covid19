package com.home.test.persist.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

public class RedisClusterTester {
    public static void main(String[] args) {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 31401));


        GenericObjectPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(20);
        poolConfig.setMaxIdle(3);
        poolConfig.setMaxWaitMillis(3000);
        poolConfig.setTestOnBorrow(true);
        JedisCluster jedis = new JedisCluster(jedisClusterNodes, 5000, 100, 10, "ENC(wcZnZ23gsuBHJGpjpPMPKUJHCaEqc26BxN+/+csntDsRGrI3ANTH0LsF+n7fM6Cr)", poolConfig);

        String key = "name";
        String value = "dummy";
        //Set a key.
        jedis.set(key, value);
        System.out.println("Set Key " + key + " Value: " + value);
        //Get the key.
        String getvalue = jedis.get(key);
        System.out.println("Get Key " + key + " ReturnValue: " + getvalue);
        jedis.close();
    }
}
