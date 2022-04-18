package com.ljy.oneclub.controller;

import com.github.pagehelper.PageHelper;
import com.ljy.oneclub.entity.*;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.*;
import com.ljy.oneclub.utils.RedisUtil;
import com.ljy.oneclub.utils.SysInfoThread;
import com.ljy.oneclub.vo.*;
import io.github.yedaxia.apidocs.ApiDoc;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

    @Autowired
    ActiveService activeService;

    @Autowired
    LikeRecordService likeRecordService;

    @Autowired
    CommentService commentService;

    @Autowired
    ActiveAndClubService activeAndClubService;

    @Autowired
    ClubMemberService clubMemberService;

    @Autowired
    MessageService messageService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    SysInfoThread sysInfoThread;


    @ApiDoc
    @RequestMapping(value = "user/updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public Msg updatePasswordInEmail(@RequestParam("new_password") String new_password,
                                     @RequestParam("old_password") String old_password,
                                     @RequestParam("again_password") String again_password,
                                     HttpSession session){
        String encodePwd="";
        String encodeOldPwd=DigestUtils.md5DigestAsHex(old_password.getBytes());
        if (!again_password.equals(new_password)){
            return Msg.fail().addData("errorInfo","前后两次密码不正确");
        }
        else {
            encodePwd=DigestUtils.md5DigestAsHex(new_password.getBytes());
        }
        User user=(User)session.getAttribute("userInfo");
        if (user!=null){
            return Msg.fail().addData("errorInfo","登录用户身份不合法");
        }
        User admin=(User)session.getAttribute("admin");
        if (admin!=null){
            if (!admin.getuPassword().equals(encodeOldPwd)){
                return Msg.fail().addData("errorInfo","旧密码输入不正确");
            }else {
                admin.setuPassword(encodePwd);
                userService.updateInfo(admin);
                session.removeAttribute("admin");
                return Msg.success();
            }
        }
        return Msg.fail().addData("errorInfo","未知错误，请稍后再试");
    }

    /**
     * 更新个人简介
     * @param session
     * @param content
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "updateInfo/intro",method = RequestMethod.POST)
    @ResponseBody
    public Msg updateIntro(HttpSession session, @Param("content") String content){
        System.out.println(content+"<======content");
        User user = (User)session.getAttribute("userInfo");
        user.setuProfile(content);
        int i = userService.updateInfo(user);
        if (i==0){
            return Msg.fail();
        }
        session.removeAttribute("userInfo");
        session.setAttribute("userInfo",user);
        return Msg.success();
    }


    @RequestMapping("index")
    public ModelAndView getIndexPage(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        User selectOne=(User)session.getAttribute("userInfo");
        if (selectOne==null){
            modelAndView.setViewName("login");
            return modelAndView;
        }
        //首页动态获取---start
        //得到与自己相关的动态，包括:自己发布的和加入社团的活动
        List<ActiveVO> activeVOList=activeService.selectActiveVOAboutByUid(selectOne.getuId());
        logger.info("用户"+selectOne.getuName()+"登录首页，获取相关动态共"+activeVOList.size()+"条");
        if (activeVOList.size()!=0){
            for (ActiveVO activeVO:activeVOList){
                //完善评论预览、关联社团ID和名字、评论数
                activeVO.setComment_count(commentService.getCommentCountByAid(activeVO.getA_id()));
                ActiveAndClubVO activeAndClubVO = activeAndClubService.getActiveAndClubVO(activeVO.getA_id());
                if (activeAndClubVO!=null){
                    activeVO.setFrom_uname(activeAndClubVO.getUname());
                    activeVO.setFrom_uid(activeAndClubVO.getUid());
                }
                int comment_count=commentService.getCommentCountByAid(activeVO.getA_id());
                //添加评论数和是否点赞和点赞数
                activeVO.setComment_count(comment_count);
                activeVO.setIsliked(likeRecordService.selectByActiveIdAndUid(activeVO.getA_id(), selectOne.getuId()).size());
                activeVO.setLiked_count(likeRecordService.getCountByActiveId(activeVO.getA_id()));
                List<Comment> comments =commentService.getTop2CommentBySourceId(activeVO.getA_id());
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
            }
            modelAndView.addObject("actives",activeVOList);
        }
        //首页动态获取---end
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("searchResult")
    public ModelAndView searchResult(@Param("content") String content, HttpSession session){
        System.out.println("搜索内容为==="+content);
        ModelAndView modelAndView = new ModelAndView();
        if (content.replace(" ","").length()==0){
            modelAndView.setViewName("error/404");
            return modelAndView;
        }
        User user= (User) session.getAttribute("userInfo");
        logger.info("用户"+user.getuName()+"使用了搜索服务");
        List<User> userList=userService.selectUserByKeyWords(content);
        modelAndView.setViewName("searchPage");
        if (userList.size()>0){
            modelAndView.addObject("userList",userList);
        }
        List<User> clubList=userService.selectClubByKeyWords(content);
        if (clubList.size()>0){
            modelAndView.addObject("clubList",clubList);
        }
        List<Active> activeList=activeService.selectArticleByKeyWords(content);
        if (activeList.size()>0){
            modelAndView.addObject("articles",activeList);
        }
        return modelAndView;
    }

    @RequestMapping("u/{userId}")
    public ModelAndView userProfile(@PathVariable(value = "userId")String uid,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        int id=0;
        try {
            id=Integer.parseInt(uid);
        } catch (NumberFormatException e) {
            modelAndView.setViewName("error/404");
            return modelAndView;
        }
        //得到用户对象
        User user = userService.selectUserById(id);
        if (user==null){
            modelAndView.setViewName("error/404");
            return modelAndView;
        }
        User thisUser=(User)session.getAttribute("userInfo");
        //如果和当前用户id相同，跳转到用户自己主页
        if (user.getuId().equals(thisUser.getuId())){
            modelAndView.setViewName("homepage");
            List<ActiveVO> activeVOList=activeService.selectHomePageActiveByUid(thisUser.getuId());
            if (activeVOList.size()!=0){
                for (ActiveVO activeVO:activeVOList){
                    //完善评论预览、关联社团ID和名字、评论数
                    activeVO.setComment_count(commentService.getCommentCountByAid(activeVO.getA_id()));
                    ActiveAndClubVO activeAndClubVO = activeAndClubService.getActiveAndClubVO(activeVO.getA_id());
                    if (activeAndClubVO!=null){
                        activeVO.setFrom_uname(activeAndClubVO.getUname());
                        activeVO.setFrom_uid(activeAndClubVO.getUid());
                    }
                    int comment_count=commentService.getCommentCountByAid(activeVO.getA_id());
                    //添加评论数和是否点赞和点赞数
                    activeVO.setComment_count(comment_count);
                    activeVO.setIsliked(likeRecordService.selectByActiveIdAndUid(activeVO.getA_id(), thisUser.getuId()).size());
                    activeVO.setLiked_count(likeRecordService.getCountByActiveId(activeVO.getA_id()));
                    List<Comment> comments =commentService.getTop2CommentBySourceId(activeVO.getA_id());
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
                }
                modelAndView.addObject("Myactives",activeVOList);
            }
            //添加收藏文章模块
            List<ActiveVO> likeArticles = activeService.selectLikeArticleByUid(thisUser.getuId());
            if (likeArticles.size()!=0){
                for (ActiveVO activeVO:likeArticles){
                    ActiveAndClubVO activeAndClubVO = activeAndClubService.getActiveAndClubVO(activeVO.getA_id());
                    if (activeAndClubVO!=null){
                        activeVO.setFrom_uname(activeAndClubVO.getUname());
                        activeVO.setFrom_uid(activeAndClubVO.getUid());
                    }
                    activeVO.setLiked_count(likeRecordService.getCountByActiveId(activeVO.getA_id()));
                }
                modelAndView.addObject("likedArticles",likeArticles);
            }
            return modelAndView;
        }
        //如果是社团用户，跳转到社团主页
        if (user.getuAuthNo()==5){
            modelAndView.setViewName("clubPage");
            modelAndView.addObject("club",user);
            int i = clubMemberService.countMembershipByClubId(user.getuId());
            modelAndView.addObject("clubCount",i);
            List<ActiveVO> activeVOList=activeService.selectHomePageActiveByUid(user.getuId());
            List<ActiveVO> active=new ArrayList<>();
            List<ActiveVO> article=new ArrayList<>();
            if (activeVOList.size()!= 0) {
                for (ActiveVO activeVO : activeVOList) {
                    //完善评论预览、关联社团ID和名字、评论数
                    activeVO.setComment_count(commentService.getCommentCountByAid(activeVO.getA_id()));
                    activeVO.setFrom_uid(user.getuId());
                    activeVO.setFrom_uname(user.getuName());
                    activeVO.setIsliked(likeRecordService.selectByActiveIdAndUid(activeVO.getA_id(), thisUser.getuId()).size());
                    activeVO.setLiked_count(likeRecordService.getCountByActiveId(activeVO.getA_id()));
                    List<Comment> comments = commentService.getTop2CommentBySourceId(activeVO.getA_id());
                    List<CommentVO> commentVOList = new ArrayList<>();
                    if (comments.size() != 0) {
                        for (Comment comment : comments) {
                            CommentVO commentVO = new CommentVO();
                            //设置评论的用户名和用户id
                            String u_name = userService.getNameById(comment.getuId());
                            commentVO.setU_id(comment.getuId());
                            commentVO.setU_name(u_name);
                            //如果回复的评论id不为空
                            if (comment.getReplyCommentId() != null) {
                                Comment comment1 = commentService.selectCommentById(comment.getReplyCommentId());
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
                    if (activeVO.getA_type() == 40) {
                        active.add(activeVO);
                    } else {
                        article.add(activeVO);
                    }
                }
                modelAndView.addObject("ClubActive", active);
                modelAndView.addObject("ClubArticle", article);
            }
            return modelAndView;
        }
        //如果是普通用户跳转到用户个人主页
        if (user.getuAuthNo()==10){
            modelAndView.setViewName("uPage");
            modelAndView.addObject("user",user);
            List<ActiveVO> activeVOList=activeService.selectHomePageActiveByUid(user.getuId());
            if (activeVOList.size()!=0){
                for (ActiveVO activeVO:activeVOList){
                    //完善评论预览、关联社团ID和名字、评论数
                    ActiveAndClubVO activeAndClubVO = activeAndClubService.getActiveAndClubVO(activeVO.getA_id());
                    if (activeAndClubVO!=null){
                        activeVO.setFrom_uname(activeAndClubVO.getUname());
                        activeVO.setFrom_uid(activeAndClubVO.getUid());
                    }
                    int comment_count=commentService.getCommentCountByAid(activeVO.getA_id());
                    //添加评论数和是否点赞和点赞数
                    activeVO.setComment_count(comment_count);
                    activeVO.setIsliked(likeRecordService.selectByActiveIdAndUid(activeVO.getA_id(), thisUser.getuId()).size());
                    activeVO.setLiked_count(likeRecordService.getCountByActiveId(activeVO.getA_id()));
                    List<Comment> comments =commentService.getTop2CommentBySourceId(activeVO.getA_id());
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
                }
                modelAndView.addObject("userActives",activeVOList);
            }
            //添加收藏文章模块
            List<ActiveVO> likeArticles = activeService.selectLikeArticleByUid(user.getuId());
            if (likeArticles.size()!=0){
                for (ActiveVO activeVO:likeArticles){
                    ActiveAndClubVO activeAndClubVO = activeAndClubService.getActiveAndClubVO(activeVO.getA_id());
                    if (activeAndClubVO!=null){
                        activeVO.setFrom_uname(activeAndClubVO.getUname());
                        activeVO.setFrom_uid(activeAndClubVO.getUid());
                    }
                    activeVO.setLiked_count(likeRecordService.getCountByActiveId(activeVO.getA_id()));
                }
                modelAndView.addObject("likedArticles",likeArticles);
            }
            return modelAndView;
        }
        //啥也不是，跳转到错误页面
        modelAndView.setViewName("error/500");
        return modelAndView;
    }

    @RequestMapping("join/{clubId}")
    public ModelAndView joinClub(@PathVariable(value = "clubId")String id,HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404");
        List<MyClub> clubList=(List<MyClub>)session.getAttribute("myclub_list");
        Iterator<MyClub> iterator = clubList.iterator();
        int clubId = 0;
        try {
            clubId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return modelAndView;
        }
        while (iterator.hasNext()){
            if (iterator.next().getClubId()==clubId){
                modelAndView.setViewName("index");
                return modelAndView;
            }
        }
        if (userService.selectUserById(clubId)==null){
            return modelAndView;
        }
        modelAndView.addObject("appToUserId",clubId);
        modelAndView.setViewName("applicationPage");
        return modelAndView;
    }


    /**
     * 得到用户列表
     * @param page 页码
     * @param pageSize 分页大小
     * @param keyword 搜索的关键词
     * @return
     */
    @ApiDoc
    @RequestMapping("user/getAll")
    @ResponseBody
    public UserTableData getAllUsers(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                     @RequestParam(value = "limit",defaultValue = "15")Integer pageSize,
                                     @RequestParam(value = "keyword",defaultValue = "null")String keyword){
        UserTableData userTableData = new UserTableData();
        if (keyword.equals("null")) {
            //开始pagehelper
            PageHelper.startPage(page,pageSize);
            List<User> users=userService.getAllUserByAid(10);
            int count=userService.countByAid(10);
            userTableData.setCount(count);
            userTableData.setData(users);
            userTableData.setCode(0);
        } else {
            //开始pagehelper
            PageHelper.startPage(page,pageSize);
            List<User> users=userService.selectUserByKeyWords(keyword);
            int count=userService.countByAidAndKeyWord(10,keyword);
            userTableData.setCount(count);
            userTableData.setData(users);
            userTableData.setCode(0);
        }
        return userTableData;
    }


    /**
     * 得到管理端首页系统数据统计
     * @return
     * @throws ParseException
     */
    @ApiDoc
    @RequestMapping("getData/admin/index")
    @ResponseBody
    public Msg getDataIndexForAdmin() throws ParseException {
        int userCount=userService.countByAid(10);
        int clubCount=userService.countByAid(5);
        int activeCount=activeService.countByPrimaryKey();
        int activeDayCount=0;
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        Date time=simpleDateFormat.parse(format);
        String timeStr = simpleDateFormat.format(time);
        DayActiveVO dayActiveVO=activeService.countActiveByDayDate(timeStr);
        if (dayActiveVO!=null){
            activeDayCount=dayActiveVO.getCount();
        }
        AdminIndexVO adminIndexVO = new AdminIndexVO(userCount,clubCount,activeCount,activeDayCount);
        return Msg.success().addData("indexData",adminIndexVO);
    }


    /**
     * 根据id删除用户
     * @param uid 用户id
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "user/delete",method = RequestMethod.POST)
    @ResponseBody
    public Msg deleteUserByUid(@RequestParam("uid")String uid){
        int userId=0;
        try {
            userId=Integer.parseInt(uid);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        userService.deleteByUid(userId);
        List<ActiveVO> activeVOList = activeService.selectHomePageActiveByUid(userId);
        for (ActiveVO activeVO:activeVOList){
            activeAndClubService.deleteByAid(activeVO.getA_id());
        }
        activeService.deleteActiveByUid(userId);
        commentService.deleteCommentByUid(userId);
        messageService.deleteMessageByUid(userId);
        return Msg.success();
    }

    /**
     * 新增社团用户
     * @param uname 社团名
     * @param password 密码
     * @param email 邮箱
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "insert/club",method = RequestMethod.POST)
    @ResponseBody
    public Msg insertClub(@RequestParam("username")String uname,
                          @RequestParam("password")String password,
                          @RequestParam("email")String email){
        if (userService.quryUserByName(uname)!=null){
            return Msg.fail().addData("errorInfo","该用户已存在！");
        }
        String encodePwd=DigestUtils.md5DigestAsHex(password.getBytes());
        User user=new User();
        user.setuName(uname);
        user.setuPassword(encodePwd);
        user.setuMailAdd(email);
        user.setuAuthNo(5);
        user.setuProfilePhotoName("https://mmad.top:82/res/avatar/user_pic.jpg");
        user.setuProfileBackgroundimgName("https://mmad.top:82/res/bkImg/defaultbkimg.jpeg");
        user.setuProfile("这家伙很懒，什么也没说。");
        userService.insertUser(user);
        return Msg.success();
    }

    /**
     * 新增用户
     * @param uname 用户名
     * @param password 密码
     * @param email 邮箱
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "insert/user")
    @ResponseBody
    public Msg insertUser(@RequestParam("username")String uname,
                          @RequestParam("password")String password,
                          @RequestParam("email")String email){
        String encodePwd=DigestUtils.md5DigestAsHex(password.getBytes());
        if (userService.quryUserByName(uname)!=null){
            return Msg.fail().addData("errorInfo","该用户已存在！");
        }
        User user=new User();
        user.setuName(uname);
        user.setuPassword(encodePwd);
        user.setuMailAdd(email);
        user.setuAuthNo(10);
        user.setuProfilePhotoName("https://mmad.top:82/res/avatar/user_pic.jpg");
        user.setuProfileBackgroundimgName("https://mmad.top:82/res/bkImg/defaultbkimg.jpeg");
        user.setuProfile("这家伙很懒，什么也没说。");
        userService.insertUser(user);
        return Msg.success();
    }


    @ApiDoc
    @RequestMapping(value = "user/updateClubInfo",method = RequestMethod.POST)
    @ResponseBody
    public Msg updateClubInfo(@RequestParam("username")String uname,
                              @RequestParam("password")String password,
                              @RequestParam("email")String email,
                              @RequestParam("clubId")Integer clubId){
        String encodePwd=DigestUtils.md5DigestAsHex(password.getBytes());
        if (userService.quryUserByName(uname)!=null){
            return Msg.fail().addData("errorInfo","该用户名已存在！");
        }
        User club=new User();
        club.setuName(uname);
        club.setuPassword(encodePwd);
        club.setuMailAdd(email);
        club.setuId(clubId);
        int i=userService.updateClubInfo(club);
        return Msg.success();
    }

    /**
     * 获取系统资源数据接口
     * @return
     */
    @ApiDoc
    @RequestMapping("system/getSysInfo")
    @ResponseBody
    public Msg getSysInfo(){
        LinkedHashMap<String, SysInfo> sysInfoMap = sysInfoThread.getSysInfoMap();
        List<String> timeList=new ArrayList<>();
        List<Integer> cpuRatio=new ArrayList<>();
        List<Integer> memoryRatio=new ArrayList<>();
        Set<String> set = sysInfoMap.keySet();
        for (String s:set){
            timeList.add(s);
            cpuRatio.add(sysInfoMap.get(s).getCpuRatio());
            memoryRatio.add(sysInfoMap.get(s).getMemoryRatio());
        }
        return Msg.success().addData("timeList",timeList).addData("cpuRatio",cpuRatio).addData("memoryRatio",memoryRatio);
    }

}
