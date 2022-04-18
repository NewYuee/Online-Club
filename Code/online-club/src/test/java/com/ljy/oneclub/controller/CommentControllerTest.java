package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.Message;
import com.ljy.oneclub.service.CommentService;
import com.ljy.oneclub.service.MessageService;
import com.ljy.oneclub.service.UserService;
import com.ljy.oneclub.vo.CommentJson;
import com.ljy.oneclub.vo.NotReadMsgVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CommentControllerTest {

    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;

    @Test
    public void getCommentsByActiveId() {
    }

    @Test
    public void testKeyword(){

    }
}