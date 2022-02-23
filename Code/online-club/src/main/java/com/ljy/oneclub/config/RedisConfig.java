package com.ljy.oneclub.config;

import com.ljy.oneclub.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {

    private String host;

    private int port;

    private int database;

    private String auth;

    @Value("${redis.host:disabled}")
    public void setHost(String host) {
        this.host = host;
    }
    @Value("${redis.port:0}")
    public void setPort(int port) {
        this.port = port;
    }
    @Value("${redis.database:0}")
    public void setDatabase(int database) {
        this.database = database;
    }
    @Value("${redis.auth}")
    public void setAuth(String auth) {
        this.auth = auth;
    }

    @Bean
    public RedisUtil getRedisUtil(){
        if (host.equals("disabled")){
            return null;
        }
        RedisUtil redisUtil=new RedisUtil();
        redisUtil.initPool(host,port,database,auth);
        return redisUtil;
    }
}
