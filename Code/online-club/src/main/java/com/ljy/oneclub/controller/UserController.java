package com.ljy.oneclub.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljy.oneclub.entity.Mail;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.MailService;
import com.ljy.oneclub.utils.RandomValidateCodeUtil;
import com.ljy.oneclub.utils.RedisUtil;
import com.ljy.oneclub.ws.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import redis.clients.jedis.Jedis;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    RedisUtil redisUtil;

//    @Autowired
//    JavaMailSenderImpl javaMailSender;
//
    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    MailService mailService;

    @RequestMapping("index/{code}")
    @ResponseBody
    public Msg index(@PathVariable int code, @RequestParam(value = "msg") String msgStr){
        Jedis jedis = redisUtil.getJedis();
        System.out.println("lianjiecheng==>"+jedis.ping());
        Msg msg = new Msg();
        return msg.success().addData("str",msgStr);
    }

    @RequestMapping("chat/{name}/{toname}")
    public String getChat(HttpSession session,@PathVariable String name,@PathVariable String toname){
        session.setAttribute("name",name);
        session.setAttribute("toname",toname);
        return "chat/chat";
    }


    @RequestMapping(value = "login/mailcode",method = RequestMethod.POST)
    public ModelAndView verifyMailCode(@RequestParam String codeInput,@RequestParam String mail){
        ModelAndView modelAndView = new ModelAndView();
        Jedis jedis = redisUtil.getJedis();
        String s = jedis.get(mail);
        redisUtil.release();
        if (s==null){
            logger.error("验证失败，邮件过期，请等待重新发送验证码");
            modelAndView.setViewName("fail");
            return modelAndView;
        }
        JSONObject jsonObject= JSON.parseObject(s);
        String requestIpAdd=jsonObject.getString("ip");
        //Object mail1 = jsonObject.get("mail");
        logger.info("ip:"+requestIpAdd+"json=>"+s);
        JSONObject jsonMail = JSON.parseObject(jsonObject.get("mail").toString());
        if (jsonMail.get("validateCode").toString().equals(codeInput)){
            logger.info("验证成功,跳转页面");
            modelAndView.setViewName("success");
            return modelAndView;
        }
        modelAndView.setViewName("fail");
        return modelAndView;
    }

    @RequestMapping("success")
    public String tes(){
        return "success";
    }

    @RequestMapping("fail")
    public String tesF(){
        return "fail";
    }


    @RequestMapping("verify")
    public String getHtml(HttpServletRequest request) throws MessagingException {
        //MailUtil.sendMail(javaMailSender,"1219904057@qq.com","主题测试Https res",text,null);
        Mail mail=new Mail("niu啊帅哥","1219904057@qq.com","One-Club通知",null,null);
        mail.setOutdate(System.currentTimeMillis()+900000);
        mail.setValidateCode("2452");
        Jedis jedis = redisUtil.getJedis();
        if(jedis.get(mail.getToAdd())!=null){
            logger.info("邮件已发，等待完成验证");
            return "mail/msg";
        };
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("mail",mail);
        String ip=request.getRemoteAddr();
        jsonObject.put("ip",ip);
        jedis.set(mail.getToAdd(),jsonObject.toJSONString());
        jedis.expire(mail.getToAdd(),60*15);
        redisUtil.release();
        mailService.sendMailHtml(templateEngine,mail);
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

            String inputStr = verifyInput.toLowerCase();
            //从session中获取随机数
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
