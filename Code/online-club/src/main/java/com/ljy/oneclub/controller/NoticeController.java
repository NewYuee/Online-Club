package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.*;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.*;
import com.ljy.oneclub.vo.ApplicationVO;
import com.ljy.oneclub.vo.NoticeOfLike;
import io.github.yedaxia.apidocs.ApiDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NoticeController {
    private static final Logger logger= LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    NoticeService noticeService;
    @Autowired
    ActiveService activeService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @Autowired
    ApplicationService applicationService;

    /**
     * 根据用户id查找点赞消息
     * @param session 当前session
     * @param userid 用户id
     * @return
     */
    @ApiDoc
    @RequestMapping("get/notice/like/{uid}")
    @ResponseBody
    public Msg getNoticeOfLike(HttpSession session, @PathVariable("uid")String userid){
        User user=(User) session.getAttribute("userInfo");
        int uid=0;
        try {
            uid=Integer.parseInt(userid);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        if (uid!=user.getuId()){
            return Msg.fail();
        }
        String type="15";
        List<NoticeOfLike> notices=noticeService.getNoticeOfLikeByUid(uid,type);
        if (notices.size()==0){
            return Msg.fail();
        }
        for (NoticeOfLike notice:notices){
            Active active = activeService.selectById(notice.getNoticeSourceId());
            if (active.getActiveType()==40){
                notice.setIsArticle(0);
                if (active.getContent().length()>5){
                    notice.setDecrips(active.getContent().substring(0,5)+"...");
                }
                else {
                    notice.setDecrips(active.getContent());
                }
            }
            if (active.getActiveType()==50){
                notice.setIsArticle(1);
                notice.setDecrips(active.getActiveTitle());
            }
        }
        return Msg.success().addData("likeNotice",notices);
    }

    /**
     * 根据id获取评论通知
     * @param session 当前session
     * @param userid 用户id
     * @return
     */
    @ApiDoc
    @RequestMapping("get/notice/comment/{uid}")
    @ResponseBody
    public Msg getCommentNotice(HttpSession session, @PathVariable("uid")String userid){
        User user=(User) session.getAttribute("userInfo");
        int uid=0;
        try {
            uid=Integer.parseInt(userid);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        if (uid!=user.getuId()){
            return Msg.fail();
        }
        List<NoticeOfLike> noticeOfLikes=noticeService.getNoticeOfComment(uid);
        if (noticeOfLikes.size()==0){
            return Msg.fail();
        }
        for (NoticeOfLike noticeOfLike:noticeOfLikes){
            //如果是回复评论
            if (noticeOfLike.getNoticeType().equals("11")){
                Comment comment=commentService.getCommentByPrimaryKey(noticeOfLike.getNoticeSourceId());
                String commentContent = comment.getCommentContent();
                if (commentContent.length()>5){
                    noticeOfLike.setDecrips(commentContent.substring(0,5)+"...");
                }
                else {
                    noticeOfLike.setDecrips(commentContent);
                }
            }else {
                //如果是动态或者文章的通知
                Active active = activeService.selectById(noticeOfLike.getNoticeSourceId());
                if (active!=null&&active.getActiveType()==40){
                    noticeOfLike.setIsArticle(0);
                    if (active.getContent().length()>5){
                        noticeOfLike.setDecrips(active.getContent().substring(0,5)+"...");
                    }
                    else {
                        noticeOfLike.setDecrips(active.getContent());
                    }
                }
                if (active!=null&&active.getActiveType()==50){
                    noticeOfLike.setIsArticle(1);
                    noticeOfLike.setDecrips(active.getActiveTitle());
                }
            }
        }
        return Msg.success().addData("commentNotice",noticeOfLikes);
    }

    /**
     * 根据id获取申请通知列表
     * @param session 当前session
     * @param userid 用户id
     * @return
     */
    @ApiDoc
    @RequestMapping("get/notice/apply/{uid}")
    @ResponseBody
    public Msg getApplicationNotice(HttpSession session, @PathVariable("uid") String userid) {
        User user = (User) session.getAttribute("userInfo");
        int uid = 0;
        try {
            uid = Integer.parseInt(userid);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        if (uid != user.getuId()) {
            return Msg.fail();
        }
        //得到已经成功或者失败的申请
        List<Application> applicationList = applicationService.getSuccessOrFailApplicationByUid(uid);
        if (applicationList.size() == 0) {
            return Msg.fail();
        }
        List<ApplicationVO> applicationVOList = new ArrayList<>();
        for (Application application : applicationList) {
            ApplicationVO applicationVO = new ApplicationVO();
            applicationVO.setApplyId(application.getAppId());
            applicationVO.setApplyToUid(application.getAppToUserId());
            Integer clubId = application.getAppToUserId();
            String clubName = userService.getNameById(clubId);
            applicationVO.setApplyToName(clubName);
            if (application.getAppType() == 1) {
                applicationVO.setType("入会申请");
            } else {
                applicationVO.setType("退会申请");
            }
            if (application.getAppStatus() == 0) {
                applicationVO.setStatus("申请失败");
            } else if (application.getAppStatus() == 1) {
                applicationVO.setStatus("申请成功");
            }
            applicationVOList.add(applicationVO);
        }
        return Msg.success().addData("appSize", applicationList.size()).addData("applications", applicationVOList);
    }
}
