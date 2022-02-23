package com.ljy.oneclub.controller;

import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.ws.rs.PathParam;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("index/{code}")
    @ResponseBody
    public Msg index(@PathVariable int code, @RequestParam(value = "msg") String msg){
        Jedis jedis = redisUtil.getJedis();
        System.out.println("lianjiecheng==>"+jedis.ping());
        System.out.println(code);
        Msg msg1 = new Msg(code, msg);
        System.out.println(msg);
        return msg1;
    }
}
