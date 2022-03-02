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
import io.github.yedaxia.apidocs.ApiDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import redis.clients.jedis.Jedis;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    Logger logger=LoggerFactory.getLogger(LoginController.class);

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    MailService mailService;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    UserService userService;

    /**
     * 返回登录注册页面
     * @return
     */
    @ApiDoc
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    /**
     * 获取验证码图片
     * @param response 返回图片
     * @param session  获取当前用户信息
     */
    @ApiDoc
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

    /**
     * 登录的验证码校验
     * @param verificationCode
     * @param session
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "login/checkVerify", headers = "Accept=application/json")
    @ResponseBody
    public BootStrapValidator checkVerify(@RequestParam String verificationCode, HttpSession session) {
        BootStrapValidator bootStrapValidator = new BootStrapValidator();
        bootStrapValidator.setValid(false);
        try{
            String inputStr = verificationCode.toLowerCase();
            //从session中获取随机数
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");

            if (random == null) {
                return bootStrapValidator;
            }
            if (random.equals(inputStr)) {
                bootStrapValidator.setValid(true);
                return bootStrapValidator;
            } else {
                logger.error("yanz验证失败："+inputStr);
                return bootStrapValidator;
            }
        }catch (Exception e){
            logger.error("验证码校验失败", e);
            return bootStrapValidator;
        }
    }

    /**
     * 登录检查用户名是否存在
     * @param r_uName 用户名
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "login/checkname" ,headers = "Accept=application/json")
    @ResponseBody
    public BootStrapValidator checkUserName(@RequestParam String r_uName){
        BootStrapValidator bootStrapValidator = new BootStrapValidator();
        bootStrapValidator.setValid(false);
        User user = userService.quryUserByName(r_uName);
        if (user==null){
            bootStrapValidator.setValid(true);
            return bootStrapValidator;
        }
        return bootStrapValidator;
    }

    /**
     * 登录检查使用邮箱是否绑定其他用户
     * @param r_email 所用邮箱
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "login/checkemail" ,headers = "Accept=application/json")
    @ResponseBody
    public BootStrapValidator checkEmailIsValid(@RequestParam String r_email){
        BootStrapValidator bootStrapValidator = new BootStrapValidator();
        bootStrapValidator.setValid(false);
        User user = userService.quryUserByEmail(r_email);
        if (user==null){
            bootStrapValidator.setValid(true);
            return bootStrapValidator;
        }
        return bootStrapValidator;
    }

    /**
     * 注册时对邮箱收到的验证码进行验证
     * @param codeInput
     * @param mail
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "login/verifymailcode",method = RequestMethod.POST)
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
     * 发送验证码到邮箱
     * @param r_uName         用户名
     * @param uPassword     密码
     * @param r_email         邮箱地址
     * @param request       当前request请求
     * @return              消息实体
     * @throws MessagingException
     */
    @ApiDoc
    @RequestMapping("register/sendmail")
    @ResponseBody
    public Msg getHtml(@RequestParam String r_uName,@RequestParam String uPassword,@RequestParam String r_email, HttpServletRequest request) throws MessagingException {
        //MailUtil.sendMail(javaMailSender,"1219904057@qq.com","主题测试Https res",text,null);
        Mail mail=new Mail(r_uName,r_email,"One-Club通知",null,null);
        mail.setOutdate(System.currentTimeMillis()+900000);
        int r= (int) (Math.random()*10000+1);
        mail.setValidateCode(String.valueOf(r));
        Jedis jedis = redisUtil.getJedis();
        if(jedis.get(mail.getToAdd())!=null){
            logger.info("邮件已发，等待完成验证");
            return Msg.fail();
        };
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("mail",mail);
        String ip=request.getRemoteAddr();
        jsonObject.put("ip",ip);
        jedis.set(mail.getToAdd(),jsonObject.toJSONString());
        jedis.expire(mail.getToAdd(),60*15);
        redisUtil.release();
        mailService.sendMailHtml(templateEngine,mail);
        return Msg.success();
    }
}
