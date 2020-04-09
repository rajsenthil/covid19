package com.home.test.persist.redis;

import com.home.test.persist.entity.User;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RedisObject {
    static ObjectMapper objectMapper;
    public void init() {
        objectMapper = new ObjectMapper();
    }


    private User createDummyUser(){

        User user = new User();

        user.name = "mkyong";
        user.age = 33;

        List<String> msg = new ArrayList<>();
        msg.add("hello jackson 1");
        msg.add("hello jackson 2");
        msg.add("hello jackson 3");

        user.messages = msg;

        return user;

    }

    public static void main(String[] args) {
        RedisObject redisObject = new RedisObject();
        if (objectMapper == null) redisObject.init();

        JedisPool pool = new JedisPool(new JedisPoolConfig(), AppConfig.getClusterIp());
        Jedis jedis = pool.getResource();
        jedis.auth("password");

        try {
            //Convert object to JSON string and save into file directly

            User user = redisObject.createDummyUser();

            String userStr = objectMapper.writeValueAsString(user);

            jedis.hset("users", user.name, userStr);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
