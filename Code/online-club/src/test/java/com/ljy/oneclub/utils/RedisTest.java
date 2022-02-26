package com.ljy.oneclub.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ljy.oneclub.config.RedisConfig;
import com.ljy.oneclub.entity.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
public class RedisTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testJSON(){
        //Jedis jedis = redisUtil.getJedis();
        Mail mail=new Mail("niu啊帅哥","1219904057@qq.com","One-Club通知",null,null);
        mail.setValidateCode("2452");
        JSONObject object=new JSONObject();
        object.put("mail",mail);
        object.put("other","kankanother");
        String strJson=object.toJSONString();
        System.out.println("jsonSt===>"+strJson);
        JSONObject jsonObject = JSON.parseObject(strJson);
        System.out.println(jsonObject.get("other"));
        System.out.println(jsonObject.get("mail"));
        Mail mail1=JSON.parseObject(jsonObject.get("mail").toString(),new TypeReference<Mail>(){});
        //Mail mail=JSON.parseObject(object1.get("mail").toString(),new TypeReference<Mail>(){});
        System.out.println(mail1.getValidateCode()+"::"+mail1.getText()+"===other==>"+jsonObject.get("other"));
    }
}
