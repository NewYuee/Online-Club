package com.ljy.oneclub.controller;

import com.github.pagehelper.PageHelper;
import com.ljy.oneclub.entity.ClubContact;
import com.ljy.oneclub.service.ClubContactService;
import com.ljy.oneclub.vo.ClubContactTableData;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ClubContactController {
    @Autowired
    ClubContactService clubContactService;

    /**
     * 根据社团id获取社团联系人
     * @param clubId
     * @return
     */
    @ApiDoc
    @RequestMapping("club/getContact")
    @ResponseBody
    public ClubContactTableData getContactByClubId(@RequestParam("clubId")Integer clubId,
                                                   @RequestParam(value = "page",defaultValue = "1") Integer page,
                                                   @RequestParam(value = "limit",defaultValue = "15")Integer pageSize,
                                                   @RequestParam(value = "keyword",defaultValue = "null")String keyword){
        int count=0;
        List<ClubContact> clubContact=null;
        if (keyword.equals("null")) {
            PageHelper.startPage(page,pageSize);
            clubContact = clubContactService.getContactInfoByClubId(clubId);
            count=clubContactService.countByClubId(clubId);
        } else {
            PageHelper.startPage(page,pageSize);
            clubContact = clubContactService.getContactInfoByClubIdAndKeyword(clubId,keyword);
            count=clubContactService.countByClubIdAndKeyword(clubId,keyword);
        }
        ClubContactTableData clubContactTableData = new ClubContactTableData();
        clubContactTableData.setCode(0);
        clubContactTableData.setCount(count);
        clubContactTableData.setData(clubContact);
        return clubContactTableData;
    }

    /**
     * 获取全部社团联系人
     * @param page
     * @param pageSize
     * @param keyword
     * @return
     */
    @ApiDoc
    @RequestMapping("club/getContact/all")
    @ResponseBody
    public ClubContactTableData getAllContactByClubId(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                                      @RequestParam(value = "limit",defaultValue = "15")Integer pageSize,
                                                      @RequestParam(value = "keyword",defaultValue = "null")String keyword){
        int count=0;
        List<ClubContact> clubContact=null;
        if (keyword.equals("null")) {
            PageHelper.startPage(page,pageSize);
            clubContact = clubContactService.getAllContactInfo();
            count=clubContactService.countAll();
        } else {
            PageHelper.startPage(page,pageSize);
            clubContact = clubContactService.getContactInfoByKeyword(keyword);
            count=clubContactService.countByKeyword(keyword);
        }
        ClubContactTableData clubContactTableData = new ClubContactTableData();
        clubContactTableData.setCode(0);
        clubContactTableData.setCount(count);
        clubContactTableData.setData(clubContact);
        return clubContactTableData;
    }
}
