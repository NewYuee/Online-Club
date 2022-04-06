package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.Message;
import com.ljy.oneclub.service.CommentService;
import com.ljy.oneclub.service.MessageService;
import com.ljy.oneclub.service.UserService;
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
        List<NotReadMsgVO> notReadMsgsByUid = messageService.getNotReadMsgsByUid("49382333");
        for (NotReadMsgVO notReadMsgVO:notReadMsgsByUid){
            System.out.println("用户"+notReadMsgVO.getUid()+"de"+notReadMsgVO.getUname()+"的未读消息数="+notReadMsgVO.getMessageCount());
            Message message = messageService.getLastNotReadMessageByUidAndToUid(notReadMsgVO.getUid(), "49382333");
            System.out.println("最后一消息为:"+message.getContent()+" "+message.getUpdateTime());
        }
    }
}