package com.ljy.oneclub.controller;

import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.MailService;
import com.ljy.oneclub.service.UserService;
import com.ljy.oneclub.utils.RedisUtil;
import io.github.yedaxia.apidocs.ApiDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import redis.clients.jedis.Jedis;

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
     * 更改密码
     * @param usermail 用户邮箱
     * @param uPassword 用户新密码
     * @param reuPassword 确认密码
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "user/updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public Msg updatePasswordInEmail(@RequestParam String usermail,
                                     @RequestParam String uPassword,
                                     @RequestParam String reuPassword){
        Jedis jedis = redisUtil.getJedis();
        String s = jedis.get(usermail);
        if (s!=null&&uPassword==reuPassword&&uPassword!=null){
            String encodePassword = DigestUtils.md5DigestAsHex(uPassword.getBytes());
            if (userService.updatePasswordByEmail(usermail,encodePassword)==0){
                return Msg.fail();
            }
            return Msg.success();
        }
        return Msg.fail();
    }


}
