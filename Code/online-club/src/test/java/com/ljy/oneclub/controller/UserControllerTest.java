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

import java.text.SimpleDateFormat;
import java.util.Date;
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
        Date date=new Date();
        long time = date.getTime();
        long l = System.currentTimeMillis();

        long outdate=l+900000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("datetime===>"+sdf.format(date));
        System.out.println("time==>"+time);
        System.out.println("current==>"+l);
        if (System.currentTimeMillis()<outdate)
            System.out.println("15mins later===>"+(l+15*60*1000));
    }


}