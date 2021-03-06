package com.ljy.oneclub.controller;

import com.github.pagehelper.PageHelper;
import com.ljy.oneclub.entity.*;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.*;
import com.ljy.oneclub.utils.RecentWeekTimeUtil;
import com.ljy.oneclub.vo.*;
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
import java.util.*;

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

    @Autowired
    NoticeService noticeService;

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
            //为点赞添加通知内容
            Notice notice = new Notice();
            notice.setNoticeUserId(user.getuId());
            notice.setNoticeToUserId(active.getuId());
            notice.setNoticeSourceId(active.getActiveId());
            notice.setNoticeStatus("0");
            notice.setNoticeType("15");
            if (!notice.getNoticeToUserId().equals(notice.getNoticeUserId())){
                noticeService.insertOne(notice);
            }
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
            noticeService.deleteRecordByUidAndAid(user.getuId(),sourceId);
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

    /**
     * 用户根据id删除动态
     * @param session
     * @return
     */
    @ApiDoc
    @RequestMapping("delete/{id}")
    @ResponseBody
    public Msg delActiveByid(@PathVariable("id")String activeId, HttpSession session){
        int aid=0;
        try {
            aid=Integer.parseInt(activeId);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        User user=(User) session.getAttribute("userInfo");
        Active active = activeService.selectById(aid);
        if (active==null){
            return Msg.fail();
        }
        if (active.getuId().equals(user.getuId())){
            int i=activeService.deleteActiveByAid(aid);
            noticeService.deleteNoticeBySourceId(aid);
            commentService.deleteCommentBySourceId(aid);
            likeRecordService.deleteBySourceId(aid);
            if (i==0){
                return Msg.fail();
            }
        }
        return Msg.success();
    }

    /**
     * 管理员根据活动id删除活动及其关联数据
     * @param aid 活动id
     * @return
     */
    @ApiDoc
    @RequestMapping(value = "admin/deleteActive",method = RequestMethod.POST)
    @ResponseBody
    public Msg adminDelActiveByid(@RequestParam("aid")Integer aid){
        Active active = activeService.selectById(aid);
        if (active==null){
            return Msg.fail();
        }
        int i=activeService.deleteActiveByAid(aid);
        noticeService.deleteNoticeBySourceId(aid);
        commentService.deleteCommentBySourceId(aid);
        likeRecordService.deleteBySourceId(aid);
        if (i==0){
            return Msg.fail();
        }
        return Msg.success();
    }

    /**
     * 获取全部活动信息
     * @param page 页码
     * @param pageSize 分页大小
     * @return
     */
    @ApiDoc
    @RequestMapping("getAll")
    @ResponseBody
    public ActiveTableData getAllActive(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit",defaultValue = "15")Integer pageSize){
        ActiveTableData activeTableData = new ActiveTableData();
        activeTableData.setCode(0);
        int count = activeService.countByPrimaryKey();
        activeTableData.setCount(count);
        PageHelper.startPage(page,pageSize);
        List<ActiveJson> activeJsons=activeService.selectAllActive();
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
     * 查询最近七天热度较高的文章动态
     * @return
     */
    @RequestMapping("getHotArticle")
    @ResponseBody
    public Msg getHotArticle(){
        Date date=new Date();
        List<Date> dates = RecentWeekTimeUtil.dateToWeek(date);
        LinkedHashMap<String, String> weekAndDate = RecentWeekTimeUtil.getWeekAndDate(dates);
        Iterator<String> iterator = weekAndDate.keySet().iterator();
        List<String> formatWeekDate=new ArrayList<>();
        while (iterator.hasNext()){
            formatWeekDate.add(weekAndDate.get(iterator.next()));
        }
        //得到最近七天的活动信息,0赞文章不统计
        List<ActiveOneDayInfo> articleAfterOneTime = activeService.getAfterOneDayActiveByType(50, formatWeekDate.get(0));
        //如果数量少于10条直接将其存入返回
        if (articleAfterOneTime.size()<=10){
            return Msg.success().addData("articles",articleAfterOneTime);
        }
        //如果跳数大于10条将进行热度计算，进行排序
        if(articleAfterOneTime.size()>10){
            for (ActiveOneDayInfo active:articleAfterOneTime){
                double hotValue=(active.getLikeCount()*0.9)+(active.getViewCount()*0.1);
                active.setHotValue(hotValue);
            }
        }
        //开始对list字段中的hotValue进行排序
        Collections.sort(articleAfterOneTime, new Comparator<ActiveOneDayInfo>() {
            @Override
            public int compare(ActiveOneDayInfo a1, ActiveOneDayInfo a2) {
                if (a1.getHotValue()<a2.getHotValue()){
                    return 1;
                }
                if (a1.getHotValue()==a2.getHotValue()){
                    return 0;
                }
                return -1;
            }
        });
        return Msg.success().addData("articles",articleAfterOneTime.subList(0,10));
    }

    /**
     * 根据请求响应获取动态数据
     * @param type 获取类型
     * @param session 当前session
     * @return
     */
    @RequestMapping("echartsData/{type}")
    @ResponseBody
    public Msg getEchartsData(@PathVariable("type")String type,HttpSession session){
        Date date=new Date();
        List<Date> dates = RecentWeekTimeUtil.dateToWeek(date);
        LinkedHashMap<String, String> weekAndDate = RecentWeekTimeUtil.getWeekAndDate(dates);
        Iterator<String> iterator = weekAndDate.keySet().iterator();
        //元素对象格式为:2022-03-04
        List<String> formatWeekDate=new ArrayList<>();
        //元素对象格式为:周一、周二、周三...
        List<String> weekNames=new ArrayList<>();
        weekNames.addAll(weekAndDate.keySet());
        ActiveEcharts activeEcharts = new ActiveEcharts();
        activeEcharts.setxAxisData(weekNames);
        List<Integer> activeCountData=new ArrayList<>();
        List<Integer> articleCountData=new ArrayList<>();
        while (iterator.hasNext()){
            formatWeekDate.add(weekAndDate.get(iterator.next()));
        }
        if (type.equals("all")){
            for (String dateStr:formatWeekDate){
                DayActiveVO actives = activeService.countOneDayActiveByType(40, dateStr);
                if (actives==null){
                    activeCountData.add(0);
                }
                else {
                    activeCountData.add(actives.getCount());
                }
                DayActiveVO articles = activeService.countOneDayActiveByType(50, dateStr);
                if (articles==null){
                    articleCountData.add(0);
                }
                else {
                    articleCountData.add(articles.getCount());
                }
            }
        }else if (type.equals("club")){
            User club=(User)session.getAttribute("admin");
            if (club==null){
                return Msg.fail();
            }
            for (String dateStr:formatWeekDate){
                DayActiveVO actives = activeService.countOneClubActiveOneTimeByType(40,dateStr,club.getuId());
                if (actives==null){
                    activeCountData.add(0);
                }
                else {
                    activeCountData.add(actives.getCount());
                }
                DayActiveVO articles = activeService.countOneClubActiveOneTimeByType(50,dateStr,club.getuId());
                if (articles==null){
                    articleCountData.add(0);
                }
                else {
                    articleCountData.add(articles.getCount());
                }
            }
        }
        activeEcharts.setActiveCountData(activeCountData);
        activeEcharts.setArticleCountData(articleCountData);
        return Msg.success().addData("echatsData",activeEcharts);
    }

}
