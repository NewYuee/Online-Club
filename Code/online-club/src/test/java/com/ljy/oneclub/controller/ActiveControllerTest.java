package com.ljy.oneclub.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.entity.Notice;
import com.ljy.oneclub.entity.User;
import com.ljy.oneclub.service.*;
import com.ljy.oneclub.vo.ActiveAndClubVO;
import com.ljy.oneclub.vo.ActiveVO;
import com.ljy.oneclub.vo.DayActiveVO;
import com.ljy.oneclub.vo.NoticeOfLike;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ActiveControllerTest {

    @Autowired
    ActiveService activeService;

    @Autowired
    ActiveAndClubService activeAndClubService;

    @Autowired
    CommentService commentService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    UserService userService;


    @Test
    public void insertActive() {
        List<NoticeOfLike> notices=noticeService.getNoticeOfLikeByUid(49382333,"13");
        System.out.println("noticeSize=>"+notices.size());
    }

    @Test
    public void insertArticle() {
        PageHelper.startPage(1,10);
        List<User> users=userService.getAllUserByAid(10);
        //PageInfo pageInfo = new PageInfo(users, 1);
        System.out.println(users.size());
    }

    @Test
    public void testActiveVO() throws ParseException {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        Date time=simpleDateFormat.parse(format);
        DayActiveVO dayActiveVO = activeService.countActiveByDayDate("2022-04-08");
        if (dayActiveVO==null){
            System.out.println("null");
        }
        System.out.println(dayActiveVO.getCount());
    }

    @Test
    public void testActiveandClub(){

    }
}