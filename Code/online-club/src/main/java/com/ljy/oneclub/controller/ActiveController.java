package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.entity.User;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.ActiveService;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("active")
public class ActiveController {
    @Autowired
    ActiveService activeService;

    /**
     * 动态发布-一般说说
     * @param inputStr 内容
     * @param session 当前session
     * @return
     * @throws ParseException
     */
    @ApiDoc
    @RequestMapping(value = "short/insertOne",method = RequestMethod.POST)
    @ResponseBody
    public Msg insertActive(@RequestParam(value = "inputStr") String inputStr, HttpSession session) throws ParseException {
        Active article = new Active();
        User userInfo = (User) session.getAttribute("userInfo");
        if (inputStr.length()==0)
            return Msg.fail().addData("dataInfo","内容不能为空");

        article.setContent(inputStr);
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        Date time=simpleDateFormat.parse(format);
        article.setUpdateTime(time);
        article.setActiveType(40);
        article.setLiked(0);
        article.setuId(userInfo.getuId());
        article.setViewedCount(0);
        int r=activeService.insertOne(article);
        if (r==0){
            return Msg.fail().addData("dataInfo","发表失败");
        }
        return Msg.success();
    }

    /**
     * 动态发布-文章
     * @param inputStr 内容
     * @param title 标题
     * @param session 当前session
     * @return
     * @throws ParseException
     */
    @ApiDoc
    @RequestMapping(value = "article/insertOne",method = RequestMethod.POST)
    @ResponseBody
    public Msg insertArticle(@RequestParam("inputStr") String inputStr,@RequestParam String title, HttpSession session) throws ParseException {
        Active article = new Active();
        User userInfo = (User) session.getAttribute("userInfo");
        if (title==null||inputStr==null)
            return Msg.fail().addData("dataInfo","标题或内容不能为空");
        article.setActiveTitle(title);
        article.setContent(inputStr);
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        Date time=simpleDateFormat.parse(format);
        article.setUpdateTime(time);
        article.setActiveType(50);
        article.setLiked(0);
        article.setuId(userInfo.getuId());
        article.setViewedCount(0);
        int res=activeService.insertOne(article);
        if (res==0){
            return Msg.fail().addData("dataInfo","发表失败");
        }
        return Msg.success();
    }
}
