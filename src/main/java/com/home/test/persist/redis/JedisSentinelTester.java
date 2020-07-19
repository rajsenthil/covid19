package com.home.test.persist.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class JedisSentinelTester {

    private static final String MASTER_NAME = "mymaster";
    private static final String PASSWORD = "ENC(wcZnZ23gsuBHJGpjpPMPKUJHCaEqc26BxN+/+csntDsRGrI3ANTH0LsF+n7fM6Cr)";
    private static final Set sentinels;
    static {
        sentinels = new HashSet();
        sentinels.add("127.0.0.1:30300");
    }

    public static void pushToRedis() {

        Jedis jedis = null;

        try {
            JedisSentinelPool pool = new JedisSentinelPool(MASTER_NAME, sentinels);
            System.out.println("Fetching connection from pool.");
            jedis = pool.getResource();
            jedis.auth(PASSWORD);
            Socket socket = jedis.getClient().getSocket();

            System.out.println("Connected to " + socket.getRemoteSocketAddress());
            int i = 0;
            while (true) {
                jedis.set("sentinel_key" + i, "value" + i);
                System.out.println(i);
                i++;
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(jedis != null)
                jedis.close();
        }

    }

    public static void main(String[] args) throws Exception {

        while(true) {

            pushToRedis();
            Thread.sleep(1000);
        }
    }
}