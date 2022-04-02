package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.service.ActiveAndClubService;
import com.ljy.oneclub.service.ActiveService;
import com.ljy.oneclub.service.CommentService;
import com.ljy.oneclub.service.UserService;
import com.ljy.oneclub.vo.ActiveAndClubVO;
import com.ljy.oneclub.vo.ActiveVO;
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


    @Test
    public void insertActive() {
        List<ActiveVO> activeVOList = activeService.selectHomePageActiveByUid(49382336);
        System.out.println(activeVOList.get(0).getU_name()+":"+activeVOList.size());
    }

    @Test
    public void insertArticle() {

    }

    @Test
    public void testActiveVO(){
    }

    @Test
    public void testActiveandClub(){

    }
}