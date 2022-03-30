package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.entity.Application;
import com.ljy.oneclub.entity.User;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.ActiveService;
import com.ljy.oneclub.service.ApplicationService;
import com.ljy.oneclub.service.MailService;
import com.ljy.oneclub.service.UserService;
import com.ljy.oneclub.utils.RedisUtil;
import com.ljy.oneclub.utils.UploadEnclosure;
import io.github.yedaxia.apidocs.ApiDoc;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @Autowired
    ActiveService activeService;


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

    /**
     * 更新个人简介
     * @param session
     * @param content
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "updateInfo/intro",method = RequestMethod.POST)
    @ResponseBody
    public Msg updateIntro(HttpSession session, @Param("content") String content){
        System.out.println(content+"<======content");
        User user = (User)session.getAttribute("userInfo");
        user.setuProfile(content);
        int i = userService.updateInfo(user);
        if (i==0){
            return Msg.fail();
        }
        session.removeAttribute("userInfo");
        session.setAttribute("userInfo",user);
        return Msg.success();
    }



    @RequestMapping("searchResult")
    public ModelAndView searchResult(@Param("content") String content, HttpSession session){
        System.out.println("搜索内容为==="+content);
        ModelAndView modelAndView = new ModelAndView();
        if (content.replace(" ","").length()==0){
            modelAndView.setViewName("error/500");
            return modelAndView;
        }
        User user= (User) session.getAttribute("userInfo");
        logger.info("用户"+user.getuName()+"使用了搜索服务");
        List<User> userList=userService.selectUserByKeyWords(content);
        modelAndView.setViewName("searchPage");
        if (userList.size()>0){
            modelAndView.addObject("userList",userList);
        }
        List<User> clubList=userService.selectClubByKeyWords(content);
        if (clubList.size()>0){
            modelAndView.addObject("clubList",clubList);
        }
        List<Active> activeList=activeService.selectArticleByKeyWords(content);
        if (activeList.size()>0){
            modelAndView.addObject("articles",activeList);
        }
        return modelAndView;
    }

    @RequestMapping("u/{userId}")
    public ModelAndView userProfile(@PathVariable(value = "userId")String uid,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        int id=0;
        try {
            id=Integer.parseInt(uid);
        } catch (NumberFormatException e) {
            modelAndView.setViewName("error/500");
            return modelAndView;
        }
        User user = userService.selectUserById(id);
        if (user==null){
            modelAndView.setViewName("error/500");
            return modelAndView;
        }
        User thisUser=(User)session.getAttribute("userInfo");
        if (user.getuId()==thisUser.getuId()){
            modelAndView.setViewName("uPage");
            return modelAndView;
        }
        if (user.getuAuthNo()==5){
            modelAndView.setViewName("clubPage");
            modelAndView.addObject("club",user);
            return modelAndView;
        }
        if (user.getuAuthNo()==10){
            modelAndView.setViewName("uPage");
            modelAndView.addObject("user",user);
            return modelAndView;
        }
        modelAndView.setViewName("error/500");
        return modelAndView;
    }

    @RequestMapping("join/{clubId}")
    public ModelAndView joinClub(@PathVariable(value = "clubId")String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/500");
        int clubId = 0;
        try {
            clubId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return modelAndView;
        }
        if (userService.selectUserById(clubId)==null){
            return modelAndView;
        }
        modelAndView.addObject("appToUserId",clubId);
        modelAndView.setViewName("applicationPage");
        return modelAndView;
    }



}
