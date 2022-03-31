package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.service.ActiveAndClubService;
import com.ljy.oneclub.service.ActiveService;
import com.ljy.oneclub.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ActiveControllerTest {

    @Autowired
    ActiveService activeService;


    @Test
    public void insertActive() {
        Active active = activeService.selectNewActiveByUid(49382336, 50);
        System.out.println("title====>"+active.getActiveTitle()+"uid===>"+active.getuId());
    }

    @Test
    public void insertArticle() {
    }
}