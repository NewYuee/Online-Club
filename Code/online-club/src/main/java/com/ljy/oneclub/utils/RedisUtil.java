package com.ljy.oneclub.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    private JedisPool jedisPool;
    private String auth;
    private Jedis jedis;

    public void initPool(String host,int port,int database,String auth){
        this.auth=auth;
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(15);
        jedisPoolConfig.setBlockWhenExhausted(true);
        jedisPoolConfig.setMaxWaitMillis(10*1000);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPool=new JedisPool(jedisPoolConfig,host,port, 2000*30,auth);
    }

    public Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        jedis.auth(auth);
        this.jedis=jedis;
        logger.info("Redis已连接");
        return jedis;
    }
    public void release(){
        if (jedis!=null){
            jedis.close();
            logger.info("Redis已释放");
        }
    }
}
