package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.entity.Comment;
import com.ljy.oneclub.entity.Notice;
import com.ljy.oneclub.entity.User;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.ActiveService;
import com.ljy.oneclub.service.CommentService;
import com.ljy.oneclub.service.NoticeService;
import com.ljy.oneclub.service.UserService;
import com.ljy.oneclub.vo.CommentVO;
import io.github.yedaxia.apidocs.ApiDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    Logger logger=LoggerFactory.getLogger(CommentController.class);
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    ActiveService activeService;
    @Autowired
    NoticeService noticeService;

    @RequestMapping(value = "comment/commit",method = RequestMethod.POST)
    @ResponseBody
    public Msg sendComment(HttpSession session,
                           @RequestParam("content")String content,
                           @RequestParam("sourceid")Integer sourceId,
                           @RequestParam("replyId")Integer replyC_id) throws ParseException {
        User user=(User)session.getAttribute("userInfo");
        Comment comment = new Comment();
        comment.setCommentContent(content);
        comment.setSourceId(sourceId);
        comment.setuId(user.getuId());
        if (replyC_id!=0){
            comment.setReplyCommentId(replyC_id);
            //回复评论，保存原评论id到notice
            Notice notice = new Notice();
            notice.setNoticeUserId(user.getuId());
            Comment comment1=commentService.getCommentByCid(replyC_id);
            notice.setNoticeToUserId(comment1.getuId());
            notice.setNoticeSourceId(replyC_id);
            notice.setNoticeStatus("0");
            notice.setNoticeType("11");
            noticeService.insertOne(notice);
        }
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        Date time=simpleDateFormat.parse(format);
        comment.setUpdateTime(time);
        int res=commentService.insertOneComment(comment);
        if (res==0){
            return Msg.fail();
        }
        //评论活动，保存活动id到notice
        Notice notice = new Notice();
        notice.setNoticeUserId(user.getuId());
        Active active = activeService.selectById(sourceId);
        notice.setNoticeToUserId(active.getuId());
        notice.setNoticeSourceId(sourceId);
        notice.setNoticeStatus("0");
        notice.setNoticeType("13");
        noticeService.insertOne(notice);
        return Msg.success();
    }

    /**
     * 根据活动Id获取评论
     * @param activeId 活动Id
     * @return
     */
    @ApiDoc
    @RequestMapping("get/comments")
    @ResponseBody
    public Msg getCommentsByActiveId(@RequestParam(value = "activeId")String activeId){
        int aId=0;
        try {
            aId = Integer.parseInt(activeId);
        } catch (NumberFormatException e) {
            return Msg.fail().addData("Info","错误参数");
        }
        List<Comment> comments =commentService.getCommentBySourceId(aId);
        List<CommentVO> commentVOList=new ArrayList<>();
        if (comments.size()!=0) {
            for (Comment comment:comments){
                CommentVO commentVO = new CommentVO();
                //设置评论的用户名和用户id
                String u_name=userService.getNameById(comment.getuId());
                commentVO.setU_id(comment.getuId());
                commentVO.setU_name(u_name);
                //如果回复的评论id不为空
                if (comment.getReplyCommentId()!=null){
                    Comment comment1=commentService.selectCommentById(comment.getReplyCommentId());
                    //根据回复的评论id找到原评论的用户名和用户id
                    commentVO.setReply_u_id(comment1.getuId());
                    commentVO.setReply_u_name(userService.getNameById(comment1.getuId()));

                }
                commentVO.setContent(comment.getCommentContent());
                commentVO.setC_id(comment.getCommentId());
                commentVOList.add(commentVO);
            }
        }
        return Msg.success().addData("comments",commentVOList);
    }

    /**
     * 根据用户id获取用自己的评论信息
     * @return
     */
    @ApiDoc
    @RequestMapping("get/comments/{uid}")
    @ResponseBody
    public Msg getCommentsByUid(@PathVariable("uid")String uid,HttpSession session){
        int user_id=0;
        try {
            user_id=Integer.parseInt(uid);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        User user=(User)session.getAttribute("userInfo");

        if (user.getuId().equals(user_id)){
            List<Comment> comments=commentService.getCommentsByUid(user_id);
            return Msg.success().addData("comments",comments);
        }
        return Msg.fail();
    }

    /**
     * 根据id删除评论
     * @param commentId 评论id
     * @param session 当前session
     * @return
     */
    @ApiDoc
    @RequestMapping("comment/delete/{commentId}")
    @ResponseBody
    public Msg deleteCommentByCid(@PathVariable("commentId")String commentId,HttpSession session){
        int cid=0;
        try {
            cid=Integer.parseInt(commentId);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        User user=(User)session.getAttribute("userInfo");
        Comment comment = commentService.getCommentByCid(cid);
        int i=0;
        if (comment.getuId().equals(user.getuId())){
            i=commentService.deleteCommentByCid(cid);
        }
        if (i==0){
            return Msg.fail();
        }
        return Msg.success();
    }
}
