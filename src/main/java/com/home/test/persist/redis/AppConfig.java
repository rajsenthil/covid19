package com.home.test.persist.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class AppConfig {
    public static Properties props = new Properties();
    static {
        System.out.println("Loading properties");
        String resourceName = "redis/redis.conf"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
            System.out.println("Properties loaded");
            Enumeration<?> propertyNames = props.propertyNames();
            while (!propertyNames.hasMoreElements()) {
                String name = (String) propertyNames.nextElement();
                System.out.println("Property name: " + name + ", value: "+props.getProperty(name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getClusterIp() {
        System.out.println("Getting ip address");
        return props.getProperty("redis.cluster.ip");
    }
}
