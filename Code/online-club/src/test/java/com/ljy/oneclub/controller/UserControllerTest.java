package com.ljy.oneclub.controller;

import com.ljy.oneclub.utils.JedisUtil;
import com.ljy.oneclub.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})

public class UserControllerTest {
//    @Autowired
//    JedisUtil jedisUtil;
//    @Test
//    public void testStreing(){
//        Jedis jedis = jedisUtil.getJedis();
//        if (jedis!=null)
//            System.out.println(jedis.ping());
//        else
//            System.out.println("null");
//    }

    @Test
    public void testA(){
        System.out.println("55666");
    }


}