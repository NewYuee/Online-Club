package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.Application;
import com.ljy.oneclub.entity.User;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.ApplicationService;
import com.ljy.oneclub.service.UserService;
import com.ljy.oneclub.utils.UploadEnclosure;
import com.ljy.oneclub.vo.ApplicationVO;
import io.github.yedaxia.apidocs.ApiDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationController {
    private final static Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    ApplicationService applicationService;

    @Autowired
    UserService userService;

    @RequestMapping("apply/{appId}")
    public ModelAndView appDetail(@PathVariable(value = "appId")String aid,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/500");
        int appid=0;
        try {
            appid=Integer.parseInt(aid);
        } catch (NumberFormatException e) {
            logger.error("查看申请表单详情输入错误参数！");
            return modelAndView;
        }
        User user=(User)session.getAttribute("userInfo");
        Application application=applicationService.selectById(appid);
        if (application==null){
            logger.error("查看表单不存在");
            return modelAndView;
        }
        if (!application.getAppUserId().equals(user.getuId())){
            logger.error("查看表单不属于当前用户:"+user.getuName());
            return modelAndView;
        }
        modelAndView.setViewName("application");
        User club=userService.selectUserById(application.getAppToUserId());
        if (club!=null){
            modelAndView.addObject("club",club);
        }
        modelAndView.addObject("applicationInfo",application);

        return modelAndView;
    }

    /**
     * 提交申请表单
     * @param multiRequest 附件信息
     * @param session 当前session信息
     * @param name 申请人姓名
     * @param gender 性别
     * @param tel 电话
     * @param detailInfo 详细信息
     * @param reason 申请理由
     * @param appToUserId 申请社团id
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping("commit/join")
    @ResponseBody
    public Msg commitJoin(MultipartHttpServletRequest multiRequest,
                          HttpSession session,
                          @RequestParam("name")String name,
                          @RequestParam("gender")String gender,
                          @RequestParam("tel")String tel,
                          @RequestParam("detailInfo")String detailInfo,
                          @RequestParam("reason")String reason,
                          @RequestParam("appToUserId")Integer appToUserId) throws IOException, ParseException {

        String filePath = UploadEnclosure.uploadImg(multiRequest);
        User user=(User)session.getAttribute("userInfo");
        Application application = new Application();
        application.setAppGender(gender);
        application.setAppUserName(name);
        application.setAppUserTelNum(tel);
        application.setAppUserId(user.getuId());
        application.setAppFile(filePath);
        application.setAppReason(reason);
        application.setAppToUserId(appToUserId);
        application.setAppType(1);
        application.setAppUserDetailInfo(detailInfo);
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        Date time=simpleDateFormat.parse(format);
        application.setAppTime(time);
        application.setAppStatus(2);
        int result=applicationService.insertOne(application);
        if (result==0){
            return Msg.fail();
        }
        return Msg.success();
    }

    /**
     * 获取用户所有申请
     * @param uid 用户id
     * @return
     */
    @ApiDoc
    @RequestMapping("application/getAll")
    @ResponseBody
    public Msg getApplications(@RequestParam("uid")String uid){
        int userId=0;
        try {
            userId=Integer.parseInt(uid);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        List<Application> applicationList=applicationService.getApplicationByUid(userId);
        if (applicationList.size()!=0){
            List<ApplicationVO> applicationVOList=new ArrayList<>();
            for (Application application:applicationList){
                ApplicationVO applicationVO = new ApplicationVO();
                applicationVO.setApplyId(application.getAppId());
                applicationVO.setApplyToUid(application.getAppToUserId());
                Integer clubId=application.getAppToUserId();
                String clubName = userService.getNameById(clubId);
                applicationVO.setApplyToName(clubName);
                if (application.getAppType()==1){
                    applicationVO.setType("入会申请");
                }
                else {
                    applicationVO.setType("退会申请");
                }
                if (application.getAppStatus()==2){
                    applicationVO.setStatus("待处理");
                }else if (application.getAppStatus()==1){
                    applicationVO.setStatus("申请成功");
                }
                else {
                    applicationVO.setStatus("申请失败");
                }
                applicationVOList.add(applicationVO);
            }
            return Msg.success().addData("appSize",applicationList.size()).addData("applications",applicationVOList);
        }
        return Msg.success().addData("appSize",0);
    }

    /**
     * 根据申请单id删除申请
     * @param session 当前申请
     * @param appId 申请单id
     * @return
     */
    @ApiDoc
    @RequestMapping("application/delete/{appId}")
    @ResponseBody
    public Msg deleteApplicationById(HttpSession session,@PathVariable("appId")String appId){
        int aId=0;
        try {
            aId=Integer.parseInt(appId);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        Application application = applicationService.selectById(aId);
        User user=(User)session.getAttribute("userInfo");
        if (user.getuId().equals(application.getAppUserId())){
            int res=applicationService.deleteApplicationById(aId);
            if (res!=0){
                return Msg.success();
            }
        }
        return Msg.fail();
    }

    @ApiDoc
    @RequestMapping("application/get/{appId}")
    @ResponseBody
    public Msg getApplication(HttpSession session,@PathVariable("appId")String appId){
        int aId=0;
        try {
            aId=Integer.parseInt(appId);
        } catch (NumberFormatException e) {
            return Msg.fail();
        }
        Application application = applicationService.selectById(aId);
        User user=(User)session.getAttribute("userInfo");
        if (application!=null&&user.getuId().equals(application.getAppUserId())){
            return Msg.success().addData("application",application);
        }
        return Msg.fail();
    }
}
