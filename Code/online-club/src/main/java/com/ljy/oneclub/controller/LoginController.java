package com.ljy.oneclub.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljy.oneclub.entity.*;
import com.ljy.oneclub.msg.BootStrapValidator;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.*;
import com.ljy.oneclub.utils.RandomValidateCodeUtil;
import com.ljy.oneclub.utils.RedisUtil;
import com.ljy.oneclub.vo.MyClub;
import com.sun.mail.smtp.DigestMD5;
import io.github.yedaxia.apidocs.ApiDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import redis.clients.jedis.Jedis;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class LoginController {

    Logger logger=LoggerFactory.getLogger(LoginController.class);

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ActiveAndClubService activeAndClubService;

    @Autowired
    MailService mailService;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    UserService userService;

    @Autowired
    ClubMemberService clubMemberService;

    @Autowired
    ActiveService activeService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeRecordService likeRecordService;

    /**
     * 返回登录注册页面
     * @return
     */
    @ApiDoc
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("userInfo");
        logger.info("用户退出登录....");
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
            logger.info("开始请求验证码");
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
                logger.error("验证失败："+inputStr);
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
     * 用户注册
     * @param emailcode     输入收到的邮箱验证码
     * @param r_email       注册使用的邮箱
     * @param r_uName       注册的用户名
     * @param uPassword     用户密码
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "register/insert",method = RequestMethod.POST)
    @ResponseBody
    public Msg verifyMailCode(@RequestParam String emailcode,@RequestParam String r_email,@RequestParam String r_uName,@RequestParam String uPassword){
        Jedis jedis = redisUtil.getJedis();
        String encodePwd=null;
        User user = new User();
        if (uPassword==null){
            return Msg.fail().addData("info","密码不能为空");
        }
        if (uPassword!=null&&uPassword.length()>=6&&uPassword.length()<20){
            encodePwd = DigestUtils.md5DigestAsHex(uPassword.getBytes());
        }
        else {
            return Msg.fail().addData("info","密码长度不规范");
        }
        user.setuPassword(encodePwd);
        user.setuName(r_uName);
        user.setuMailAdd(r_email);
        user.setuAuthNo(10);
        if (user.getuMailAdd()==null){
            return Msg.fail().addData("info","输入邮箱不存在");
        }
        String s = jedis.get(user.getuMailAdd());
        redisUtil.release();
        if (s==null){
            logger.error("验证失败，邮件过期，请等待重新发送验证码");
            return Msg.fail().addData("info","邮件过期，请重新发送验证码");
        }
        JSONObject jsonObject= JSON.parseObject(s);
        String requestIpAdd=jsonObject.getString("ip");
        logger.info("ip:"+requestIpAdd+"json=>"+s);
        JSONObject jsonMail = JSON.parseObject(jsonObject.get("mail").toString());
        if (emailcode!=null&&jsonMail.getString("validateCode").equals(emailcode)){
            logger.info("注册验证成功");
            user.setuProfilePhotoName("user_pic.jpg");
            user.setuProfileBackgroundimgName("defaultbkimg.jpeg");
            user.setuProfile("这家伙很懒，什么也没说。");
            int i = userService.insertUser(user);
            Long del = jedis.del(user.getuMailAdd());
            if (del==1){
                logger.error("redis清楚缓存成功");
            }
            redisUtil.release();
            if (i==0){
                return Msg.fail().addData("info","注册失败，请稍后再试");
            }
            return Msg.success();
        }
        else {
            logger.info("注册验证失败");
            Msg.fail().addData("info","验证码错误");
        }
        logger.info("注册失败了");
        return Msg.fail().addData("info","验证失败，请稍后再试");
    }

    /**
     * 发送验证码到邮箱
     * @param r_uName         用户名
     * @param r_email         邮箱地址
     * @param request       当前request请求
     * @return              消息实体
     * @throws MessagingException
     */
    @ApiDoc
    @RequestMapping(value = "register/sendmail",method = RequestMethod.POST)
    @ResponseBody
    public Msg getHtml(@RequestParam String r_uName , @RequestParam String r_email, HttpServletRequest request) throws MessagingException {
        if (r_uName==null||r_email==null){
            return Msg.fail().addData("info","邮箱或账号为空");
        }
        Mail mail=new Mail(r_uName,r_email,"One-Club通知",null,null);
        mail.setOutdate(System.currentTimeMillis()+900000);
        int r= (int) (Math.random()*10000+1);
        mail.setValidateCode(String.valueOf(r));
        Jedis jedis = redisUtil.getJedis();
        if(jedis.get(mail.getToAdd())!=null){
            logger.info("邮件已发，等待完成验证");
            return Msg.fail().addData("info","邮件已发，等待完成验证");
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

    /**
     * 用户登录
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "user/login")
    @ResponseBody
    public Msg login(@RequestParam String verificationCode,User user,HttpSession session){
        String encodePwd = DigestUtils.md5DigestAsHex(user.getuPassword().getBytes());
        logger.info("encodePwd=>"+encodePwd);
        user.setuPassword(encodePwd);
        user.setuAuthNo(10);
        Object attribute = session.getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
        if (!attribute.equals(verificationCode)){
            logger.error("验证码错误");
            return Msg.fail().addData("info","验证码错误");
        }
        User selectOne = userService.selectOne(user);
        //登录成功，加载首页资源
        if (selectOne!=null){
            //查找用户当前加入的社团
            List<ClubMember> clubMemberList =clubMemberService.selectMyClubByUid(selectOne.getuId());
            List<MyClub> clubList=new ArrayList<>();
            if (clubMemberList!=null){
                for (ClubMember clubMember:clubMemberList){
                    MyClub myClub = new MyClub();
                    myClub.setClubId(clubMember.getClubId());
                    String clubName=userService.queryNameById(clubMember.getClubId());
                    myClub.setClubName(clubName);
                    clubList.add(myClub);
                }
                session.removeAttribute("myclub_list");
                session.setAttribute("myclub_list",clubList);
            }
            session.removeAttribute("userInfo");
            session.removeAttribute("admin");
            session.setAttribute("userInfo",selectOne);
            return Msg.success();
        }
        return Msg.fail().addData("info","账号密码错误");
    }

    /**
     * 返回admin登录界面
     * @return
     */
    @RequestMapping("admin")
    public String loadAdminLoginPage(HttpSession session){
        session.removeAttribute("admin");
        return "admin/login";
    }

    /**
     * 后台登录处理,成功返回对应管理界面
     * @param user 登录用户对象
     * @param session
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "admin/login")
    @ResponseBody
    public Msg adminLogin(User user,HttpSession session){
        if (user.getuName()==null||user.getuPassword()==null){
            return Msg.fail();
        }
        String encodePwd=DigestUtils.md5DigestAsHex(user.getuPassword().getBytes());
        user.setuPassword(encodePwd);
        User one = userService.selectOne(user);
        if (one!=null){
            logger.info("管理员:"+one.getuName()+"登录了后台");
            session.removeAttribute("userInfo");
            session.removeAttribute("admin");
            session.setAttribute("admin",one);
        }
        else {
            return Msg.fail();
        }
        if (one.getuAuthNo().equals(5)){
            return Msg.success().addData("auth",5);
        }else if (one.getuAuthNo().equals(1)){
            return Msg.success().addData("auth",1);
        }
        return Msg.fail();
    }



}
