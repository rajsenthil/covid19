package com.home.test.persist.redis;

import redis.clients.jedis.*;

import java.util.Set;

public class RedisPipeline {
    public static void main(String[] args) {

        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis("172.17.0.9", 6379);
//        JedisCluster jedis = new JedisCluster(new HostAndPort(AppConfig.getClusterIp(), 6379));
        System.out.println("Connection to server sucessfully");

        String userOneId = "4352523";
        String userTwoId = "4849888";

        Pipeline p = jedis.pipelined();
        p.sadd("searched#" + userOneId, "paris");
        p.zadd("ranking", 126, userOneId);
        p.zadd("ranking", 325, userTwoId);
        Response<Boolean> pipeExists = p.sismember("searched#" + userOneId, "paris");
        Response<Set<String>> pipeRanking = p.zrange("ranking", 0, -1);
        p.sync();

        Boolean exists = pipeExists.get();
        System.out.println("Pipeline exists: " + exists);
        Set<String> ranking = pipeRanking.get();
    }
}
