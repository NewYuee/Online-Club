package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.entity.ActiveAndClub;
import com.ljy.oneclub.entity.User;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.ActiveAndClubService;
import com.ljy.oneclub.service.ActiveService;
import com.ljy.oneclub.service.UserService;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("active")
public class ActiveController {
    @Autowired
    ActiveService activeService;

    @Autowired
    UserService userService;

    @Autowired
    ActiveAndClubService activeAndClubService;

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


    @RequestMapping("article/{articleId}")
    public ModelAndView getArticleInfo(@PathVariable(value = "articleId") String articleId){
        ModelAndView modelAndView = new ModelAndView();
        int aId=0;
        try {
            int i = Integer.parseInt(articleId);
            aId=i;
        } catch (NumberFormatException e) {
            modelAndView.setViewName("error/500");
            return modelAndView;
        }
        Active active=activeService.selectById(aId);
        if (active!=null){
            int viewCount=active.getViewedCount();
            viewCount+=2;
            active.setViewedCount(viewCount);
            activeService.updateViewCount(active);
            modelAndView.addObject("active",active);
            User user=userService.selectUserById(active.getuId());
            List<ActiveAndClub> activeAndClub=activeAndClubService.selectOneByActiveId(active.getActiveId());
            if (activeAndClub.size()!=0){
                User fromClub = userService.selectUserById(activeAndClub.get(0).getFromClubId());
                modelAndView.addObject("fromClub",fromClub);
            }
            modelAndView.addObject("author",user);
            modelAndView.setViewName("newsDetail");
        }
        if (active==null){
            modelAndView.setViewName("error/500");
        }
        return modelAndView;
    }
}
