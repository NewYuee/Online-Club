package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.ClubMemberMapper;
import com.ljy.oneclub.entity.ClubMember;
import com.ljy.oneclub.entity.ClubMemberExample;
import com.ljy.oneclub.service.ClubMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClubMemberServiceImpl implements ClubMemberService {

    @Autowired
    ClubMemberMapper clubMemberMapper;

    @Override
    public List<ClubMember> selectMyClubByUid(Integer getuId) {
        ClubMemberExample clubMemberExample = new ClubMemberExample();
        ClubMemberExample.Criteria criteria = clubMemberExample.createCriteria();
        criteria.andUserIdEqualTo(getuId);
        List<ClubMember> clubMemberList = clubMemberMapper.selectByExample(clubMemberExample);
        return clubMemberList;
    }
}
