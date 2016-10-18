package com.baidu.gdcd.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtil {

    private static final String IP = "127.0.0.1"; // "开发机ip"
    private static final int PORT = 8888; // redis端口
    private static final int TIME_OUT = 6000; // 链接超时设置

    private static Jedis jedis = null;

    public static Jedis getRedisInstance() {
        try {
            if (jedis == null) {
                GenericObjectPoolConfig config = new GenericObjectPoolConfig();
                config.setMaxTotal(1000);
                config.setMaxIdle(100);
                config.setMinIdle(8);
                config.setMaxWaitMillis(-1);
                JedisPool jedisPool = new JedisPool(config, IP, PORT, TIME_OUT);
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jedis;
    }

}
