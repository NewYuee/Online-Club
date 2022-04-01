package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.*;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.*;
import com.ljy.oneclub.vo.ActiveVO;
import com.ljy.oneclub.vo.CommentVO;
import com.ljy.oneclub.vo.MyClub;
import io.github.yedaxia.apidocs.ApiDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("active")
public class ActiveController {

    Logger logger= LoggerFactory.getLogger(ActiveController.class);

    @Autowired
    ActiveService activeService;

    @Autowired
    UserService userService;

    @Autowired
    ActiveAndClubService activeAndClubService;

    @Autowired
    LikeRecordService likeRecordService;

    @Autowired
    CommentService commentService;

    /**
     * 动态发布-一般说说
     * @param inputStr 内容
     * @param session 当前session
     * @return
     * @throws ParseException
     */
    @ApiDoc
    @RequestMapping(value = "short/insertOne",method = RequestMethod.POST)
    @ResponseBody
    public Msg insertActive(@RequestParam(value = "inputStr") String inputStr,
                            HttpSession session,
                            @RequestParam(value = "clubname")String clubname) throws ParseException {
        Active article = new Active();
        User userInfo = (User) session.getAttribute("userInfo");
        if (inputStr.length()==0)
            return Msg.fail().addData("dataInfo","内容不能为空");

        article.setContent(inputStr);
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        Date time=simpleDateFormat.parse(format);
        article.setUpdateTime(time);
        article.setActiveType(40);
        article.setLiked(0);
        article.setuId(userInfo.getuId());
        article.setViewedCount(0);
        int r=activeService.insertOne(article);
        if (r==0){
            return Msg.fail().addData("dataInfo","发表失败");
        }
        System.out.println("绑定的社团为"+clubname);
        //对动态进行话题绑定操作
        if (!clubname.equals("空")){
            User club = userService.quryUserByName(clubname);
            Active active=activeService.selectNewActiveByUid(userInfo.getuId(),40);
            ActiveAndClub activeAndClub = new ActiveAndClub();
            activeAndClub.setActiveId(active.getActiveId());
            activeAndClub.setFromClubId(club.getuId());
            int res=activeAndClubService.insertOne(activeAndClub);
        }
        return Msg.success();
    }

    /**
     * 动态发布-文章
     * @param inputStr 内容
     * @param title 标题
     * @param session 当前session
     * @return
     * @throws ParseException
     */
    @ApiDoc
    @RequestMapping(value = "article/insertOne",method = RequestMethod.POST)
    @ResponseBody
    public Msg insertArticle(@RequestParam("inputStr") String inputStr,
                             @RequestParam String title,
                             @RequestParam("clubname")String clubname,
                             HttpSession session) throws ParseException {
        Active article = new Active();
        User userInfo = (User) session.getAttribute("userInfo");
        if (title==null||inputStr==null)
            return Msg.fail().addData("dataInfo","标题或内容不能为空");
        article.setActiveTitle(title);
        article.setContent(inputStr);
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        Date time=simpleDateFormat.parse(format);
        article.setUpdateTime(time);
        article.setActiveType(50);
        article.setLiked(0);
        article.setuId(userInfo.getuId());
        article.setViewedCount(0);
        int res=activeService.insertOne(article);
        if (res==0){
            return Msg.fail().addData("dataInfo","发表失败");
        }
        //对动态进行话题绑定操作
        if (!clubname.equals("空")){
            User club = userService.quryUserByName(clubname);
            Active active=activeService.selectNewActiveByUid(userInfo.getuId(),50);
            ActiveAndClub activeAndClub = new ActiveAndClub();
            activeAndClub.setActiveId(active.getActiveId());
            activeAndClub.setFromClubId(club.getuId());
            activeAndClubService.insertOne(activeAndClub);
        }
        return Msg.success();
    }


    @RequestMapping("article/{articleId}")
    public ModelAndView getArticleInfo(@PathVariable(value = "articleId") String articleId){
        ModelAndView modelAndView = new ModelAndView();
        int aId=0;
        try {
            int i = Integer.parseInt(articleId);
            aId=i;
        } catch (NumberFormatException e) {
            modelAndView.setViewName("error/500");
            return modelAndView;
        }
        Active active=activeService.selectById(aId);
        if (active!=null){
            int viewCount=active.getViewedCount();
            viewCount+=2;
            active.setViewedCount(viewCount);
            activeService.updateViewCount(active);
            modelAndView.addObject("active",active);
            User user=userService.selectUserById(active.getuId());
            //根据活动id查询关联的社团
            List<ActiveAndClub> activeAndClub=activeAndClubService.selectOneByActiveId(active.getActiveId());
            if (activeAndClub.size()!=0){
                User fromClub = userService.selectUserById(activeAndClub.get(0).getFromClubId());
                modelAndView.addObject("fromClub",fromClub);
            }
            modelAndView.addObject("author",user);
            modelAndView.setViewName("newsDetail");
        }
        if (active==null){
            modelAndView.setViewName("error/500");
        }
        return modelAndView;
    }

    /**
     * 根据资源id对活动进行点赞
     * @param sourceId 活动id
     * @param session 当前session
     * @return
     * @throws ParseException
     */
    @ApiDoc
    @RequestMapping(value = "like/{sourceId}",method = RequestMethod.POST)
    @ResponseBody
    public Msg collectActive(@PathVariable("sourceId")Integer sourceId,HttpSession session) throws ParseException {
        LikedRecord likedRecord = new LikedRecord();
        likedRecord.setLikeActiveId(sourceId);
        Active active = activeService.selectById(sourceId);
        likedRecord.setLikeType(active.getActiveType());
        User user=(User)session.getAttribute("userInfo");
        likedRecord.setuId(user.getuId());
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        Date time=simpleDateFormat.parse(format);
        likedRecord.setLikeTime(time);
        int res=userService.collectActive(likedRecord);
        if (res!=0){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 根据资源id对活动取消点赞
     * @param sourceId 活动id
     * @param session 当前session
     * @return
     * @throws ParseException
     */
    @ApiDoc
    @RequestMapping(value = "unlike/{sourceId}",method = RequestMethod.POST)
    @ResponseBody
    public Msg uncollectActive(@PathVariable("sourceId")Integer sourceId,HttpSession session){
        User user=(User)session.getAttribute("userInfo");
        List<LikedRecord> likedRecords=likeRecordService.selectByActiveIdAndUid(sourceId,user.getuId());
        if (likedRecords.size()==0){
            return Msg.fail();
        };
        int res=likeRecordService.uncollectActiveByActiveIdAndUid(sourceId,user.getuId());
        if (res!=0){
            return Msg.success();
        }
        return Msg.fail();
    }


    /**
     * 根据活动id获取点赞数，以及是否点赞
     * @param sourceId 活动id
     * @param session 当前session
     * @return
     */
    @ApiDoc
    @RequestMapping("getLikeNum/{sourceId}")
    @ResponseBody
    public Msg getLikeNum(@PathVariable("sourceId")String sourceId,HttpSession session){
        int aid=0;
        try {
            aid=Integer.parseInt(sourceId);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        User user=(User)session.getAttribute("userInfo");
        int count=likeRecordService.getCountByActiveId(aid);
        List<LikedRecord> likedRecords = likeRecordService.selectByActiveIdAndUid(aid, user.getuId());
        if (likedRecords.size()==0){
            return Msg.success().addData("num",count).addData("islike",0);
        }
        return Msg.success().addData("num",count).addData("islike",1);
    }

    @RequestMapping("getIndex")
    @ResponseBody
    public Msg getAllActiveIndex(HttpSession session){
        User user=(User) session.getAttribute("userInfo");
        List<ActiveVO> activeVOList=new ArrayList<>();
        //得到与自己相关的动态，包括:自己发布的和加入社团的活动
        List<Active> activeList=activeService.selectActiveAboutByUid(user.getuId());
        logger.info("用户"+user.getuName()+"登录首页，获取相关动态共"+activeList.size()+"条");
        for (Active active:activeList){
            User userInfo = userService.selectUserById(active.getuId());
            List<ActiveAndClub> activeAndClubs = activeAndClubService.selectOneByActiveId(active.getActiveId());
            ActiveVO activeVO = new ActiveVO();
            if (activeAndClubs.size()!=0){
                activeVO.setFrom_uid(activeAndClubs.get(0).getFromClubId());
                activeVO.setFrom_uname(userService.getNameById(activeAndClubs.get(0).getFromClubId()));
            }
            activeVO.setProfile_name(userInfo.getuProfilePhotoName());
            activeVO.setBkpicName(userInfo.getuProfileBackgroundimgName());
            if (active.getActiveType()==50){
                activeVO.setTitle(active.getActiveTitle());
            }else {
                activeVO.setContent(active.getContent());
            }
            activeVO.setComment_count(commentService.getCommentCountByAid(active.getActiveId()));
            List<Comment> comments =commentService.getTop2CommentBySourceId(active.getActiveId());
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
                activeVO.setCommentVOList(commentVOList);
            }
            activeVOList.add(activeVO);
        }
        return Msg.success().addData("actives",activeVOList);
    }
}
