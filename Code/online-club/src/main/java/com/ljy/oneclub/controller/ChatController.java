package com.ljy.oneclub.controller;

import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("chat")
public class ChatController {
    /**
     * 聊天接口
     * @param session     当前session
     * @param userId      当前用户id
     * @param toUserId    聊天对象id
     * @return
     */
    @ApiDoc
    @RequestMapping("chat/{userId}/{toUserId}")
    public String getChat(HttpSession session, @PathVariable String userId, @PathVariable String toUserId){
        session.setAttribute("name",userId);
        session.setAttribute("toname",toUserId);
        return "chat/chat";
    }
}
