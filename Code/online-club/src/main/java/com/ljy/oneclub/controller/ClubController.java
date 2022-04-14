package com.ljy.oneclub.controller;

import com.github.pagehelper.PageHelper;
import com.ljy.oneclub.entity.*;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.*;
import com.ljy.oneclub.vo.*;
import io.github.yedaxia.apidocs.ApiDoc;
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
public class ClubController {

    @Autowired
    UserService userService;

    @Autowired
    ClubMemberService clubMemberService;

    @Autowired
    ActiveService activeService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    ActiveAndClubService activeAndClubService;

    @Autowired
    ClubContactService clubContactService;

    @Autowired
    CommentService commentService;

    /**
     * 根据用户id查找其加入的社团数
     * @param userId 用户id
     * @return
     */
    @ApiDoc
    @RequestMapping("club/getCount/{uid}")
    @ResponseBody
    public Msg getClubCountByUid(@PathVariable("uid")String userId){
        int uid=0;
        try {
            uid = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        List<ClubMember> clubMemberList = clubMemberService.selectMyClubByUid(uid);
        return Msg.success().addData("clubCount",clubMemberList.size());
    }

    /**
     * 得到社团列表
     * @param page 页数
     * @param pageSize 分页大小
     * @param keyword 搜索的关键词
     * @return
     */
    @ApiDoc
    @RequestMapping("club/getAll")
    @ResponseBody
    public ClubTableData getAllClub(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                    @RequestParam(value = "limit",defaultValue = "15")Integer pageSize,
                                    @RequestParam(value = "keyword",defaultValue = "null")String keyword){
        ClubTableData clubTableData = new ClubTableData();
        //开始pagehelper
        List<User> users;
        int count;
        if (keyword.equals("null")) {
            PageHelper.startPage(page,pageSize);
            users = userService.getAllUserByAid(5);
            count = userService.countByAid(5);
        } else {
            PageHelper.startPage(page,pageSize);
            users = userService.selectClubByKeyWords(keyword);
            count = userService.countByAidAndKeyWord(5,keyword);
        }
        List<ClubTableJson> clubTableJsonList=new ArrayList<>();
        for (User user:users){
            ClubTableJson clubTableJson = new ClubTableJson();
            clubTableJson.setClubId(user.getuId());
            clubTableJson.setClubName(user.getuName());
            int activeCount=activeService.countActiveByUid(user.getuId());
            int membershipCount=clubMemberService.countMembershipByClubId(user.getuId());
            clubTableJson.setActiveCount(activeCount);
            clubTableJson.setMemberships(membershipCount);
            clubTableJsonList.add(clubTableJson);
        }
        clubTableData.setCount(count);
        clubTableData.setData(clubTableJsonList);
        clubTableData.setCode(0);
        return clubTableData;
    }

    /**
     * 更新社团信息
     * @param username 社团名
     * @param email 邮箱地址
     * @param profile 简介
     * @param session 当前session
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "club/updateInfo",method = RequestMethod.POST)
    @ResponseBody
    public Msg updateClubInfo(@RequestParam("username") String username,
                              @RequestParam("email") String email,
                              @RequestParam("profile") String profile,
                              HttpSession session){
        User admin=(User) session.getAttribute("admin");
        if (admin!=null&&admin.getuName().equals(username)){
            admin.setuProfile(profile);
            admin.setuMailAdd(email);
            userService.updateInfo(admin);
            session.removeAttribute("admin");
            session.setAttribute("admin",admin);
            return Msg.success();
        }
        return Msg.fail();
    }


    /**
     * 获取管理端首页统计数据
     * @param session
     * @return
     * @throws ParseException
     */
    @ApiDoc
    @RequestMapping("getData/club/index")
    @ResponseBody
    public Msg getClubIndexData(HttpSession session) throws ParseException {
        User admin=(User)session.getAttribute("admin");
        if (admin==null){
            return Msg.fail();
        }
        int membershipCount = clubMemberService.countMembershipByClubId(admin.getuId());
        int notDealApplicationCount=applicationService.countNotDealApplicationByClubid(admin.getuId());
        int activeCountInClub=activeAndClubService.countByClubId(admin.getuId());
        int dayActiveCount=0;
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        Date time=simpleDateFormat.parse(format);
        String timeStr = simpleDateFormat.format(time);
        DayActiveVO dayActiveVO =activeService.countDayActiveByClubIdAndDateTime(admin.getuId(),timeStr);
        if (dayActiveVO!=null){
            dayActiveCount=dayActiveVO.getCount();
        }
        ClubIndexData clubIndexData=new ClubIndexData(notDealApplicationCount,membershipCount,activeCountInClub,dayActiveCount);
        return Msg.success().addData("indexData",clubIndexData);
    }

    /**
     * 根据当前session获取事务申请
     * @param page 页码
     * @param pageSize 分页大小
     * @param session 当前session
     * @return
     */
    @ApiDoc
    @RequestMapping("club/getApply/all")
    @ResponseBody
    public ClubAppTable getApplyList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                     @RequestParam(value = "limit",defaultValue = "15")Integer pageSize,
                                     HttpSession session){
        ClubAppTable tableData = new ClubAppTable();
        tableData.setCode(0);
        User admin=(User)session.getAttribute("admin");
        if (admin==null){
            tableData.setCode(1);
            return tableData;
        }
        tableData.setCount(applicationService.countApplicationByClubId(admin.getuId()));
        PageHelper.startPage(page,pageSize);
        List<ApplicationJson> applicationJsons=applicationService.getApplicationByClubId(admin.getuId());
        tableData.setData(applicationJsons);
        return tableData;
    }

    /**
     * 获取社团成员
     * @param page 页码
     * @param pageSize 页大小
     * @param keyword 用户名关键词
     * @param session 当前session
     * @return
     */
    @ApiDoc
    @RequestMapping("club/getMember")
    @ResponseBody
    public ClubMemberTableData getMemberByClubId(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                                 @RequestParam(value = "limit",defaultValue = "15")Integer pageSize,
                                                 @RequestParam(value = "keyword",defaultValue = "null")String keyword,
                                                 HttpSession session){
        ClubMemberTableData clubMember = new ClubMemberTableData();
        clubMember.setCode(0);
        User club=(User)session.getAttribute("admin");
        if (club==null){
            return clubMember;
        }
        int count = clubMemberService.countMembershipByClubId(club.getuId());
        clubMember.setCount(count);
        if (keyword.equals("null")){
            PageHelper.startPage(page,pageSize);
            List<ClubMember> members=clubMemberService.selectMemberByClubId(club.getuId());
            clubMember.setData(members);
        }
        else{
            int nums=clubMemberService.countClubMembersLikeName("%"+keyword+"%",club.getuId());
            clubMember.setCount(nums);
            PageHelper.startPage(page,pageSize);
            List<ClubMember> members=clubMemberService.selectClubMembersLikeName("%"+keyword+"%",club.getuId());
            clubMember.setData(members);
        }
        return clubMember;
    }

    /**
     * 根据id删除社团成员
     * @param session 当前session
     * @param mId 成员id
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "club/delete/member",method = RequestMethod.POST)
    @ResponseBody
    public Msg delMemberById(HttpSession session,@RequestParam("memberId")Integer mId){
        ClubMember clubMember=clubMemberService.selectById(mId);
        User club=(User)session.getAttribute("admin");
        if (club==null){
            return Msg.fail();
        }
        if (clubMember.getClubId().equals(club.getuId())){
            int i=clubMemberService.deleteById(mId);
        }
        return Msg.success();
    }

    /**
     * 添加社团成员
     * @param session 当前session
     * @param clubMember 成员对象
     * @return
     * @throws ParseException
     */
    @ApiDoc
    @RequestMapping(value = "club/addMember",method = RequestMethod.POST)
    @ResponseBody
    public Msg addClubMember(HttpSession session,ClubMember clubMember) throws ParseException {
        User club=(User)session.getAttribute("admin");
        if (club==null){
            return Msg.fail().addData("errorInfo","没有登录");
        }
        clubMember.setClubId(club.getuId());
        clubMember.setUserId(404);
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        Date time=simpleDateFormat.parse(format);
        clubMember.setMemJoinTime(time);
        int i = clubMemberService.insertClubMember(clubMember);
        if (i==0){
            return Msg.fail().addData("errorInfo","添加失败！");
        }
        return Msg.success();
    }

    /**
     * 新增社团联系人
     * @param session 当前社团session
     * @param clubContact 插入的联系人对象
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "club/addContact",method = RequestMethod.POST)
    @ResponseBody
    public Msg addClubContact(HttpSession session, ClubContact clubContact){
        User club=(User)session.getAttribute("admin");
        if (club==null){
            return Msg.fail().addData("errorInfo","请登录！");
        }
        clubContact.setClubId(club.getuId());
        int i=clubContactService.insertOne(clubContact);
        if (i==0){
            return Msg.fail().addData("errorInfo","添加失败，未知错误,稍后再试！");
        }
        return Msg.success();
    }

    /**
     * 社团发布一般动态
     * @param session 当前社团session
     * @param content 发送的内容
     * @return
     * @throws ParseException
     */
    @ApiDoc
    @RequestMapping(value = "club/sendActive",method = RequestMethod.POST)
    @ResponseBody
    public Msg addActive(HttpSession session, @RequestParam("content")String content) throws ParseException {
        User club=(User)session.getAttribute("admin");
        if (club==null){
            return Msg.fail().addData("errorInfo","请登录！");
        }
        Active active = new Active();
        active.setuId(club.getuId());
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        Date time=simpleDateFormat.parse(format);
        active.setUpdateTime(time);
        active.setActiveType(40);
        active.setLiked(0);
        active.setViewedCount(0);
        active.setContent(content);
        int res = activeService.insertOne(active);
        if (res==0){
            return Msg.fail().addData("errorInfo","发送失败，未知错误,稍后再试！");
        }
        Active newestActive = activeService.selectNewActiveByUid(club.getuId(), 40);
        ActiveAndClub activeAndClub = new ActiveAndClub();
        activeAndClub.setActiveId(newestActive.getActiveId());
        activeAndClub.setFromClubId(club.getuId());
        int i=activeAndClubService.insertOne(activeAndClub);
        if (i==0){
            return Msg.fail().addData("errorInfo","发表成功但动态绑定失败，稍后再试！");
        }
        return Msg.success();
    }


    /**
     * 社团发布文章/新闻
     * @param session 当前社团的session
     * @param content 发布的内容
     * @param title 标题
     * @return
     * @throws ParseException
     */
    @ApiDoc
    @RequestMapping(value = "club/sendArticle",method = RequestMethod.POST)
    @ResponseBody
    public Msg addArticle(HttpSession session, @RequestParam("content")String content,@RequestParam("title")String title) throws ParseException {
        User club=(User)session.getAttribute("admin");
        if (club==null){
            return Msg.fail().addData("errorInfo","请登录！");
        }
        Active active = new Active();
        active.setuId(club.getuId());
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        Date time=simpleDateFormat.parse(format);
        active.setUpdateTime(time);
        active.setActiveType(50);
        active.setLiked(0);
        active.setViewedCount(0);
        active.setContent(content);
        active.setActiveTitle(title);
        int res = activeService.insertOne(active);
        if (res==0){
            return Msg.fail().addData("errorInfo","发送失败，未知错误,稍后再试！");
        }
        Active newestActive = activeService.selectNewActiveByUid(club.getuId(), 50);
        ActiveAndClub activeAndClub = new ActiveAndClub();
        activeAndClub.setActiveId(newestActive.getActiveId());
        activeAndClub.setFromClubId(club.getuId());
        int i=activeAndClubService.insertOne(activeAndClub);
        if (i==0){
            return Msg.fail().addData("errorInfo","发表成功但社团绑定失败，稍后再试！");
        }
        return Msg.success();
    }


    /**
     * 社团活动列表
     * @param page 页码
     * @param pageSize 分页大小
     * @param session 当前session
     * @return
     */
    @ApiDoc
    @RequestMapping("club/active/getAll")
    @ResponseBody
    public ActiveTableData getAllClubActive(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                            @RequestParam(value = "limit",defaultValue = "15")Integer pageSize,
                                            HttpSession session){
        User club=(User)session.getAttribute("admin");
        ActiveTableData activeTableData = new ActiveTableData();
        activeTableData.setCode(0);
        if (club==null){
            activeTableData.setCount(0);
            return activeTableData;
        }
        int count = activeService.countActiveByUid(club.getuId());
        activeTableData.setCount(count);
        PageHelper.startPage(page,pageSize);
        List<ActiveJson> activeJsons=activeService.selectAllActiveByClubId(club.getuId());
        if (activeJsons.size()==0){
            activeTableData.setData(activeJsons);
            return activeTableData;
        }
        for (ActiveJson activeJson:activeJsons){
            //获取评论数
            activeJson.setComment_count(commentService.getCommentCountByAid(activeJson.getA_id()));
            Active active = activeService.selectById(activeJson.getA_id());
            if (active.getActiveType()==40){
                activeJson.setOmitContent(active.getContent());
            }else if (active.getActiveType()==50){
                activeJson.setOmitContent("《"+active.getActiveTitle()+"》");
            }
        }
        activeTableData.setData(activeJsons);
        return activeTableData;
    }

    /**
     * 根据申请id处理事务
     * @param appId 申请id
     * @param type 处理结果(成功/失败)
     * @param dealResult 处理意见
     * @return
     * @throws ParseException
     */
    @ApiDoc
    @RequestMapping(value = "club/dealApply",method = RequestMethod.POST)
    @ResponseBody
    public Msg dealApplyById(@RequestParam("appId")Integer appId,
                             @RequestParam("type")Integer type,
                             @RequestParam("other")String dealResult) throws ParseException {
        Application application = new Application();
        application.setAppId(appId);
        if (dealResult.length()==0){
            application.setAppDealResult("无");
        }else {
            application.setAppDealResult(dealResult);
        }
        application.setAppStatus(type);
        int i=applicationService.updateApplicationSelect(application);
        if (i==0){
            return Msg.fail();
        }
        if (type==0){
            return Msg.success();
        }
        if (type==1){
            Application sourceInfo = applicationService.selectById(appId);
            int res=0;
            if (sourceInfo.getAppType()==1){
                ClubMember clubMember = new ClubMember();
                clubMember.setClubId(sourceInfo.getAppToUserId());
                clubMember.setUserId(sourceInfo.getAppUserId());
                clubMember.setMemName(sourceInfo.getAppUserName());
                clubMember.setGender(sourceInfo.getAppGender());
                clubMember.setMemTelNum(sourceInfo.getAppUserTelNum());
                clubMember.setMemDetailInfo(sourceInfo.getAppUserDetailInfo());
                Date date=new Date();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(date);
                Date time=simpleDateFormat.parse(format);
                clubMember.setMemJoinTime(time);
                res=clubMemberService.insertClubMember(clubMember);
            }
            else if (sourceInfo.getAppType()==2){
                res=clubMemberService.deleteClubMemberByUidAndClubId(sourceInfo.getAppUserId(),sourceInfo.getAppToUserId());
            }
        }

        return Msg.success();
    }
}
