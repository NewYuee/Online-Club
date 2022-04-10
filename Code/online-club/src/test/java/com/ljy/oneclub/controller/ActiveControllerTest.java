package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.entity.Notice;
import com.ljy.oneclub.service.*;
import com.ljy.oneclub.vo.ActiveAndClubVO;
import com.ljy.oneclub.vo.ActiveVO;
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


    @Test
    public void insertActive() {
        List<NoticeOfLike> notices=noticeService.getNoticeOfLikeByUid(49382333,"13");
        System.out.println("noticeSize=>"+notices.size());
    }

    @Test
    public void insertArticle() {
        //List<ActiveVO> activeVOList=activeService.selectLikeArticleByUid(49382336);
        //System.out.println(activeVOList.size());
    }

    @Test
    public void testActiveVO(){
    }

    @Test
    public void testActiveandClub(){

    }
}