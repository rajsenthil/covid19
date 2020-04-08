package com.home.test.persist.redis;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RedisKeyJava {
    public static void main(String[] args) {

        //Connecting to Redis server on localhost
//        Jedis jedis = new Jedis("172.17.0.4");
        JedisPool pool = new JedisPool(new JedisPoolConfig(), AppConfig.getClusterIp());
        Jedis jedis = pool.getResource();
        jedis.auth("password");

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

    }

}
