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
            return Msg.fail().addData("errorInfo","???????????????????????????");
        }
        else {
            encodePwd=DigestUtils.md5DigestAsHex(new_password.getBytes());
        }
        User user=(User)session.getAttribute("userInfo");
        if (user!=null){
            return Msg.fail().addData("errorInfo","???????????????????????????");
        }
        User admin=(User)session.getAttribute("admin");
        if (admin!=null){
            if (!admin.getuPassword().equals(encodeOldPwd)){
                return Msg.fail().addData("errorInfo","????????????????????????");
            }else {
                admin.setuPassword(encodePwd);
                userService.updateInfo(admin);
                session.removeAttribute("admin");
                return Msg.success();
            }
        }
        return Msg.fail().addData("errorInfo","??????????????????????????????");
    }

    /**
     * ??????????????????
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
        //??????????????????---start
        //???????????????????????????????????????:???????????????????????????????????????
        List<ActiveVO> activeVOList=activeService.selectActiveVOAboutByUid(selectOne.getuId());
        logger.info("??????"+selectOne.getuName()+"????????????????????????????????????"+activeVOList.size()+"???");
        if (activeVOList.size()!=0){
            for (ActiveVO activeVO:activeVOList){
                //?????????????????????????????????ID?????????????????????
                activeVO.setComment_count(commentService.getCommentCountByAid(activeVO.getA_id()));
                ActiveAndClubVO activeAndClubVO = activeAndClubService.getActiveAndClubVO(activeVO.getA_id());
                if (activeAndClubVO!=null){
                    activeVO.setFrom_uname(activeAndClubVO.getUname());
                    activeVO.setFrom_uid(activeAndClubVO.getUid());
                }
                int comment_count=commentService.getCommentCountByAid(activeVO.getA_id());
                //??????????????????????????????????????????
                activeVO.setComment_count(comment_count);
                activeVO.setIsliked(likeRecordService.selectByActiveIdAndUid(activeVO.getA_id(), selectOne.getuId()).size());
                activeVO.setLiked_count(likeRecordService.getCountByActiveId(activeVO.getA_id()));
                List<Comment> comments =commentService.getTop2CommentBySourceId(activeVO.getA_id());
                List<CommentVO> commentVOList=new ArrayList<>();
                if (comments.size()!=0) {
                    for (Comment comment:comments){
                        CommentVO commentVO = new CommentVO();
                        //?????????????????????????????????id
                        String u_name=userService.getNameById(comment.getuId());
                        commentVO.setU_id(comment.getuId());
                        commentVO.setU_name(u_name);
                        //?????????????????????id?????????
                        if (comment.getReplyCommentId()!=null){
                            Comment comment1=commentService.selectCommentById(comment.getReplyCommentId());
                            //?????????????????????id????????????????????????????????????id
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
        //??????????????????---end
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("searchResult")
    public ModelAndView searchResult(@Param("content") String content, HttpSession session){
        System.out.println("???????????????==="+content);
        ModelAndView modelAndView = new ModelAndView();
        if (content.replace(" ","").length()==0){
            modelAndView.setViewName("error/404");
            return modelAndView;
        }
        User user= (User) session.getAttribute("userInfo");
        logger.info("??????"+user.getuName()+"?????????????????????");
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
        //??????????????????
        User user = userService.selectUserById(id);
        if (user==null){
            modelAndView.setViewName("error/404");
            return modelAndView;
        }
        User thisUser=(User)session.getAttribute("userInfo");
        //?????????????????????id????????????????????????????????????
        if (user.getuId().equals(thisUser.getuId())){
            modelAndView.setViewName("homepage");
            List<ActiveVO> activeVOList=activeService.selectHomePageActiveByUid(thisUser.getuId());
            if (activeVOList.size()!=0){
                for (ActiveVO activeVO:activeVOList){
                    //?????????????????????????????????ID?????????????????????
                    activeVO.setComment_count(commentService.getCommentCountByAid(activeVO.getA_id()));
                    ActiveAndClubVO activeAndClubVO = activeAndClubService.getActiveAndClubVO(activeVO.getA_id());
                    if (activeAndClubVO!=null){
                        activeVO.setFrom_uname(activeAndClubVO.getUname());
                        activeVO.setFrom_uid(activeAndClubVO.getUid());
                    }
                    int comment_count=commentService.getCommentCountByAid(activeVO.getA_id());
                    //??????????????????????????????????????????
                    activeVO.setComment_count(comment_count);
                    activeVO.setIsliked(likeRecordService.selectByActiveIdAndUid(activeVO.getA_id(), thisUser.getuId()).size());
                    activeVO.setLiked_count(likeRecordService.getCountByActiveId(activeVO.getA_id()));
                    List<Comment> comments =commentService.getTop2CommentBySourceId(activeVO.getA_id());
                    List<CommentVO> commentVOList=new ArrayList<>();
                    if (comments.size()!=0) {
                        for (Comment comment:comments){
                            CommentVO commentVO = new CommentVO();
                            //?????????????????????????????????id
                            String u_name=userService.getNameById(comment.getuId());
                            commentVO.setU_id(comment.getuId());
                            commentVO.setU_name(u_name);
                            //?????????????????????id?????????
                            if (comment.getReplyCommentId()!=null){
                                Comment comment1=commentService.selectCommentById(comment.getReplyCommentId());
                                //?????????????????????id????????????????????????????????????id
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
            //????????????????????????
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
        //?????????????????????????????????????????????
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
                    //?????????????????????????????????ID?????????????????????
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
                            //?????????????????????????????????id
                            String u_name = userService.getNameById(comment.getuId());
                            commentVO.setU_id(comment.getuId());
                            commentVO.setU_name(u_name);
                            //?????????????????????id?????????
                            if (comment.getReplyCommentId() != null) {
                                Comment comment1 = commentService.selectCommentById(comment.getReplyCommentId());
                                //?????????????????????id????????????????????????????????????id
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
        //????????????????????????????????????????????????
        if (user.getuAuthNo()==10){
            modelAndView.setViewName("uPage");
            modelAndView.addObject("user",user);
            List<ActiveVO> activeVOList=activeService.selectHomePageActiveByUid(user.getuId());
            if (activeVOList.size()!=0){
                for (ActiveVO activeVO:activeVOList){
                    //?????????????????????????????????ID?????????????????????
                    ActiveAndClubVO activeAndClubVO = activeAndClubService.getActiveAndClubVO(activeVO.getA_id());
                    if (activeAndClubVO!=null){
                        activeVO.setFrom_uname(activeAndClubVO.getUname());
                        activeVO.setFrom_uid(activeAndClubVO.getUid());
                    }
                    int comment_count=commentService.getCommentCountByAid(activeVO.getA_id());
                    //??????????????????????????????????????????
                    activeVO.setComment_count(comment_count);
                    activeVO.setIsliked(likeRecordService.selectByActiveIdAndUid(activeVO.getA_id(), thisUser.getuId()).size());
                    activeVO.setLiked_count(likeRecordService.getCountByActiveId(activeVO.getA_id()));
                    List<Comment> comments =commentService.getTop2CommentBySourceId(activeVO.getA_id());
                    List<CommentVO> commentVOList=new ArrayList<>();
                    if (comments.size()!=0) {
                        for (Comment comment:comments){
                            CommentVO commentVO = new CommentVO();
                            //?????????????????????????????????id
                            String u_name=userService.getNameById(comment.getuId());
                            commentVO.setU_id(comment.getuId());
                            commentVO.setU_name(u_name);
                            //?????????????????????id?????????
                            if (comment.getReplyCommentId()!=null){
                                Comment comment1=commentService.selectCommentById(comment.getReplyCommentId());
                                //?????????????????????id????????????????????????????????????id
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
            //????????????????????????
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
        //????????????????????????????????????
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
     * ??????????????????
     * @param page ??????
     * @param pageSize ????????????
     * @param keyword ??????????????????
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
            //??????pagehelper
            PageHelper.startPage(page,pageSize);
            List<User> users=userService.getAllUserByAid(10);
            int count=userService.countByAid(10);
            userTableData.setCount(count);
            userTableData.setData(users);
            userTableData.setCode(0);
        } else {
            //??????pagehelper
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
     * ???????????????????????????????????????
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
     * ??????id????????????
     * @param uid ??????id
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
     * ??????????????????
     * @param uname ?????????
     * @param password ??????
     * @param email ??????
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "insert/club",method = RequestMethod.POST)
    @ResponseBody
    public Msg insertClub(@RequestParam("username")String uname,
                          @RequestParam("password")String password,
                          @RequestParam("email")String email){
        if (userService.quryUserByName(uname)!=null){
            return Msg.fail().addData("errorInfo","?????????????????????");
        }
        String encodePwd=DigestUtils.md5DigestAsHex(password.getBytes());
        User user=new User();
        user.setuName(uname);
        user.setuPassword(encodePwd);
        user.setuMailAdd(email);
        user.setuAuthNo(5);
        user.setuProfilePhotoName("https://mmad.top:82/res/avatar/user_pic.jpg");
        user.setuProfileBackgroundimgName("https://mmad.top:82/res/bkImg/defaultbkimg.jpeg");
        user.setuProfile("????????????????????????????????????");
        userService.insertUser(user);
        return Msg.success();
    }

    /**
     * ????????????
     * @param uname ?????????
     * @param password ??????
     * @param email ??????
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
            return Msg.fail().addData("errorInfo","?????????????????????");
        }
        User user=new User();
        user.setuName(uname);
        user.setuPassword(encodePwd);
        user.setuMailAdd(email);
        user.setuAuthNo(10);
        user.setuProfilePhotoName("https://mmad.top:82/res/avatar/user_pic.jpg");
        user.setuProfileBackgroundimgName("https://mmad.top:82/res/bkImg/defaultbkimg.jpeg");
        user.setuProfile("????????????????????????????????????");
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
            return Msg.fail().addData("errorInfo","????????????????????????");
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
     * ??????????????????????????????
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
