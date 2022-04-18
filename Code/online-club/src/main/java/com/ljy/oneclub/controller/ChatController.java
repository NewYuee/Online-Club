package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.Message;
import com.ljy.oneclub.entity.User;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.MessageService;
import com.ljy.oneclub.service.UserService;
import com.ljy.oneclub.vo.NotReadMsgVO;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ChatController {
    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    /**
     * 聊天页面
     * @param session     当前session
     * @param userId      当前用户id
     * @param toUserId    聊天对象id
     * @return
     */
    @RequestMapping("chat/{userId}/{toUserId}")
    public ModelAndView getChat(HttpSession session, @PathVariable String userId, @PathVariable String toUserId){
        User user=(User)session.getAttribute("userInfo");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/500");
        int uid=0;
        int toUid=0;
        try {
            uid=Integer.parseInt(userId);
            toUid=Integer.parseInt(toUserId);
        } catch (NumberFormatException e) {

            return modelAndView;
        }
        if (uid!=user.getuId()){
            return modelAndView;
        }
        //userId:当前用户id;touserId:聊天的用户的id
        List<Message> messageList=messageService.getAllNotMsgsByUids(userId,toUserId);
        //更新未读消息为已读
        for (Message message:messageList){
            message.setIsread("1");
            messageService.updateMsgById(message);
        }
        modelAndView.setViewName("chat/chatPage");
        User toUser = userService.selectUserById(toUid);
        modelAndView.addObject("toUser",toUser);
        return modelAndView;
    }

    /**
     * 根据用户id和聊天对象id获取最近聊天记录，默认5条
     * @param session 当前session
     * @param uId 当前用户id
     * @param toUid 聊天对象id
     * @return
     */
    @ApiDoc
    @RequestMapping("get5ChatMsgs/{uId}/{toUid}")
    @ResponseBody
    public Msg get5Messages(HttpSession session,
                            @PathVariable("uId")String uId,
                            @PathVariable("toUid")String toUid){
        int uid=0;
        int touid=0;
        try {
            uid=Integer.parseInt(uId);
            touid=Integer.parseInt(toUid);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        User user=(User)session.getAttribute("userInfo");
        if (user.getuId()!=uid){
            return Msg.fail();
        }
        List<Message> messageList=messageService.selectMessagesByUidAndToUidLimit5(uid,touid);
        return Msg.success().addData("messages",messageList);
    }

    /**
     * 根据用户id和聊天对象id获取全部聊天记录
     * @param session 当前session
     * @param uId 当前用户id
     * @param toUid 聊天对象id
     * @return Msg 响应对象
     */
    @ApiDoc
    @RequestMapping("getAllMsgs/{uId}/{toUid}")
    @ResponseBody
    public Msg getAllMsgByUidAndToUid(HttpSession session,
                                      @PathVariable("uId")String uId,
                                      @PathVariable("toUid")String toUid){
        int uid=0;
        int touid=0;
        try {
            uid=Integer.parseInt(uId);
            touid=Integer.parseInt(toUid);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        User user=(User)session.getAttribute("userInfo");
        if (user.getuId()!=uid){
            return Msg.fail();
        }
        List<Message> messageList=messageService.selectMessagesByUidAndToUid(uid,touid);
        return Msg.success().addData("messages",messageList);
    }


    /**
     * 根据用户id获取未读消息，由未读消息列表解析
     * @param session 当前session
     * @param uid 当前用户id
     * @return
     */
    @ApiDoc
    @RequestMapping("getNotReadMsgs/{uid}")
    @ResponseBody
    public Msg getMessageNotRead(HttpSession session, @PathVariable("uid")String uid){
        int userId=0;
        try {
            userId=Integer.parseInt(uid);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        User user=(User)session.getAttribute("userInfo");
        if (user.getuId()!=userId){
            return Msg.fail();
        }
        List<NotReadMsgVO> notReadMsgs = messageService.getNotReadMsgsByUid(uid);
        for(NotReadMsgVO notReadMsgVO:notReadMsgs){
            Message message=messageService.getLastNotReadMessageByUidAndToUid(notReadMsgVO.getUid(),uid);
            notReadMsgVO.setLastTime(message.getUpdateTime());
            notReadMsgVO.setMessageContent(message.getContent());
            notReadMsgVO.setNowUid(userId);
        }
        return Msg.success().addData("notReadMessages",notReadMsgs);
    }

}
