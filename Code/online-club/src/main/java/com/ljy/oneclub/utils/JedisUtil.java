package com.ljy.oneclub.utils;

import redis.clients.jedis.Jedis;

public class JedisUtil {
    private String host;
    private int port;
    private String auth;



    public Jedis getJedis(){
        //int p = Integer.parseInt(port);
        Jedis jedis = new Jedis(host, port);
        jedis.auth(auth);
        return jedis;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setHost(String host) {
        this.host=host;
    }
}
