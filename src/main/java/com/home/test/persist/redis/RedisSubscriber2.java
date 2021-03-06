package com.home.test.persist.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

public class RedisSubscriber2 extends JedisPubSub {

   private static Logger logger = LoggerFactory.getLogger(RedisSubscriber2.class);

    @Override
    public void onMessage(String channel, String message) {
        logger.info("Message received. Channel: {}, Msg: {}", channel, message);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {

    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {

    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {

    }
}