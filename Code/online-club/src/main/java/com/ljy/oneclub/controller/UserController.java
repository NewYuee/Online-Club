package com.ljy.oneclub.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljy.oneclub.entity.Mail;
import com.ljy.oneclub.entity.User;
import com.ljy.oneclub.msg.BootStrapValidator;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.MailService;
import com.ljy.oneclub.service.UserService;
import com.ljy.oneclub.utils.RandomValidateCodeUtil;
import com.ljy.oneclub.utils.RedisUtil;
import com.ljy.oneclub.ws.WebSocketServer;
import io.github.yedaxia.apidocs.ApiDoc;
import io.github.yedaxia.apidocs.Ignore;
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

    @Autowired
    UserService userService;


    /**
     *试试index
     * @param code 代码
     * @param msgStr msg的string实体
     * @return
     */
    @ApiDoc
    @RequestMapping("index/{code}")
    @ResponseBody
    public Msg index(@PathVariable int code, @RequestParam(value = "msg") String msgStr){
        Jedis jedis = redisUtil.getJedis();
        System.out.println("lianjiecheng==>"+jedis.ping());
        Msg msg = new Msg();
        return msg.success().addData("str",msgStr);
    }
    @RequestMapping(value = "user/login",method = RequestMethod.POST)
    public String login(User user){
        User userone = userService.selectOne(user);
        if (userone!=null){
            return "success";
        }
        return "login";
    }

    @RequestMapping("chat/{name}/{toname}")
    public String getChat(HttpSession session,@PathVariable String name,@PathVariable String toname){
        session.setAttribute("name",name);
        session.setAttribute("toname",toname);
        return "chat/chat";
    }

    /**
     * 验证邮箱验证码
     * @param codeInput 输入的验证码
     * @param mail      当前邮箱账号
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "login/mailcode",method = RequestMethod.POST)
    public String verifyMailCode(@RequestParam String codeInput,@RequestParam String mail){
        Jedis jedis = redisUtil.getJedis();
        String s = jedis.get(mail);
        redisUtil.release();
        if (s==null){
            logger.error("验证失败，邮件过期，请等待重新发送验证码");
            return "fail";
        }
        JSONObject jsonObject= JSON.parseObject(s);
        String requestIpAdd=jsonObject.getString("ip");
        //Object mail1 = jsonObject.get("mail");
        logger.info("ip:"+requestIpAdd+"json=>"+s);
        JSONObject jsonMail = JSON.parseObject(jsonObject.get("mail").toString());
        if (jsonMail.get("validateCode").toString().equals(codeInput)){
            logger.info("验证成功,跳转页面");
            return "success";
        }
        return "fail";
    }

    /**
     * 返回成功页面
     * @return
     */
    @ApiDoc
    @RequestMapping("success")
    public String tes(){
        return "success";
    }

    /**
     * 返回错误页面
     * @return
     */
    @ApiDoc
    @RequestMapping("fail")
    public String tesF(){
        return "fail";
    }




    /**
     * 验证验证码是否正确
     * @param verifyInput 输入的验证码
     * @param other       额外参数
     * @param session     当前session
     * @return 布尔类型
     */
    @ApiDoc
    @RequestMapping(value = "login/checkvalidate", method = RequestMethod.POST,headers = "Accept=application/json")
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
