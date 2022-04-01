package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.service.ActiveAndClubService;
import com.ljy.oneclub.service.ActiveService;
import com.ljy.oneclub.service.CommentService;
import com.ljy.oneclub.service.UserService;
import com.ljy.oneclub.vo.ActiveAndClubVO;
import com.ljy.oneclub.vo.ActiveVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ActiveControllerTest {

    @Autowired
    ActiveService activeService;

    @Autowired
    ActiveAndClubService activeAndClubService;

    @Autowired
    CommentService commentService;


    @Test
    public void insertActive() {
        Active active = activeService.selectNewActiveByUid(49382336, 50);
        System.out.println("title====>"+active.getActiveTitle()+"uid===>"+active.getuId());
    }

    @Test
    public void insertArticle() {
        long time=System.currentTimeMillis();
        List<Active> activeList = activeService.selectActiveAboutByUid(49382336);
        System.out.println("active count==>"+activeList.size());
        System.out.println("active id   ==>"+activeList.get(0).getActiveId());
        System.out.println("no.1 content==>"+activeList.get(0).getContent());
        System.out.println("no.1 time==>"+activeList.get(0).getUpdateTime());
        System.out.println("耗时:"+(System.currentTimeMillis()-time));
    }

    @Test
    public void testActiveVO(){
        List<ActiveVO> activeVOList = activeService.selectActiveVOAboutByUid(49382336);
        System.out.println(activeVOList.size());
        int num=1;
        for (ActiveVO activeVO:activeVOList){

            System.out.print(num+":tile="+activeVO.getTitle());
            System.out.print(":content="+activeVO.getContent());
            System.out.println(":a_type="+activeVO.getA_type());
            num++;
        }
    }

    @Test
    public void testActiveandClub(){
        List<ActiveVO> activeVOList = activeService.selectActiveVOAboutByUid(49382336);
        for (ActiveVO activeVO:activeVOList){
            ActiveAndClubVO activeAndClubVO = activeAndClubService.getActiveAndClubVO(activeVO.getA_id());
            if (activeAndClubVO!=null){
                activeVO.setFrom_uid(activeAndClubVO.getUid());
                activeVO.setFrom_uname(activeAndClubVO.getUname());
            }
            System.out.println("active id=>"+activeVO.getA_id()+"active comment_count=>"+commentService.getCommentCountByAid(activeVO.getA_id()));
        }
    }
}