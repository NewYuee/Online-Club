package com.ljy.oneclub.controller;

import com.ljy.oneclub.service.CommentService;
import com.ljy.oneclub.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CommentControllerTest {

    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @Test
    public void getCommentsByActiveId() {
        String nameById = userService.getNameById(49382336);
        System.out.println("name===>"+nameById);
    }
}