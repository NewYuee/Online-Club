package com.ljy.oneclub.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private JedisPool jedisPool;
    private String auth;

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
        return jedis;
    }
}
