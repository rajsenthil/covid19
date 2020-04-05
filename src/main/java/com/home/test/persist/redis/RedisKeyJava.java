package com.home.test.persist.redis;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RedisKeyJava {
    public static void main(String[] args) {

        //Connecting to Redis server on localhost
//        Jedis jedis = new Jedis("172.17.0.4");
        JedisCluster jedis = new JedisCluster(new HostAndPort(AppConfig.getClusterIp(),6379));
        System.out.println("Connection to server sucessfully");
        //store data in redis keys
        // Get the stored data and print it
        Set<String> keys = jedis.keys("{*}");
        Iterator<String> iterator = keys.iterator();

        /*for(int i = 0; i<keys.size(); i++) {
            System.out.println("List of stored keys:: "+keys.get(i));
        }*/
        while (iterator.hasNext()) {
            System.out.println("List of stored keys:: "+iterator.next());
        }

        testRedis(jedis);
    }

    public static void testRedis(JedisCluster cluster) {
        ScanParams scanParams = new ScanParams().count(1000);
        Set<String> allKeys = new HashSet<>();
        cluster.getClusterNodes().values().forEach((pool) -> {
            String cur = ScanParams.SCAN_POINTER_START;
            do {
                try (Jedis jedis = pool.getResource()) {
                    ScanResult<String> scanResult = jedis.scan(cur, scanParams);
                    allKeys.addAll(scanResult.getResult());
//                    cur = scanResult.getStringCursor();
                    cur = scanResult.getCursor();
                }
            } while (!cur.equals(ScanParams.SCAN_POINTER_START));
        });
        allKeys.stream().forEach(System.out::println);
    }
}
