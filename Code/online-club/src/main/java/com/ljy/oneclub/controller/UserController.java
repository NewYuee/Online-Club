package com.ljy.oneclub.controller;

import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.utils.RandomValidateCodeUtil;
import com.ljy.oneclub.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;

@Controller
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

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

    @RequestMapping("verify")
    public String getHtml(){
        return "verify";
    }

    @RequestMapping("login/getVerify")
    public void getVerify(HttpServletResponse response, HttpSession session){
        try{
            response.setContentType("img/jpeg");
            response.setHeader("Pragma","No-cache");
            response.setHeader("Cache-Control","no-cache");
            response.setDateHeader("Expire",0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(session, response);//输出验证码图片方法
        } catch (Exception e) {
            logger.error("获取失败》》"+e);
        }
    }

    @RequestMapping(value = "login/checkVerify", method = RequestMethod.POST,headers = "Accept=application/json")
    @ResponseBody
    public boolean checkVerify(@RequestParam String verifyInput,@RequestParam String other, HttpSession session) {
        try{
            //从session中获取随机数
            String inputStr = verifyInput;
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            logger.info("get a param:"+other);
            if (random == null) {
                return false;
            }
            if (random.equals(inputStr)) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            logger.error("验证码校验失败", e);
            return false;
        }
    }
}
