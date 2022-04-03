package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.ClubMember;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.ClubMemberService;
import com.ljy.oneclub.service.UserService;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ClubController {

    @Autowired
    UserService userService;

    @Autowired
    ClubMemberService clubMemberService;

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
}
