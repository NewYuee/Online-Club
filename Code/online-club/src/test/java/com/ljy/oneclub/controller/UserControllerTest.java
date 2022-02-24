package com.ljy.oneclub.controller;

import com.ljy.oneclub.utils.JedisUtil;
import com.ljy.oneclub.utils.RandomValidateCodeUtil;
import com.ljy.oneclub.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})

public class UserControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
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
        logger.info("nba啊哈哈哈");
        logger.error("哦豁l");
    }


}