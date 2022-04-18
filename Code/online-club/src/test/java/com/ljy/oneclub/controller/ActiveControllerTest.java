package com.ljy.oneclub.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljy.oneclub.entity.SysInfo;
import com.ljy.oneclub.service.*;
import com.ljy.oneclub.utils.SysInfoUtil;
import com.sun.management.OperatingSystemMXBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;

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

    @Autowired
    ClubMemberService clubMemberService;


    @Test
    public void insertActive() {
        System.out.println("noticeSize=>");
    }

    @Test
    public void testSystemUsage() throws InterruptedException {
    }

    @Test
    public void testMap() throws InterruptedException {
    }

}