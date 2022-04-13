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

    @Override
    public int countMembershipByClubId(Integer getuId) {
        ClubMemberExample clubMemberExample = new ClubMemberExample();
        ClubMemberExample.Criteria criteria = clubMemberExample.createCriteria();
        criteria.andClubIdEqualTo(getuId);
        return clubMemberMapper.countByExample(clubMemberExample);
    }

    @Override
    public int insertClubMember(ClubMember clubMember) {
        return clubMemberMapper.insertSelective(clubMember);
    }

    @Override
    public int deleteClubMemberByUidAndClubId(Integer appUserId, Integer appToUserId) {
        ClubMemberExample clubMemberExample = new ClubMemberExample();
        ClubMemberExample.Criteria criteria = clubMemberExample.createCriteria();
        criteria.andClubIdEqualTo(appToUserId);
        criteria.andUserIdEqualTo(appUserId);
        return clubMemberMapper.deleteByExample(clubMemberExample);
    }

    @Override
    public List<ClubMember> selectMemberByClubId(Integer getuId) {
        ClubMemberExample clubMemberExample = new ClubMemberExample();
        ClubMemberExample.Criteria criteria = clubMemberExample.createCriteria();
        criteria.andClubIdEqualTo(getuId);
        return clubMemberMapper.selectByExample(clubMemberExample);
    }

    @Override
    public int countClubMembersLikeName(String s,Integer clubId) {
        ClubMemberExample clubMemberExample = new ClubMemberExample();
        ClubMemberExample.Criteria criteria = clubMemberExample.createCriteria();
        criteria.andClubIdEqualTo(clubId);
        criteria.andMemNameLike(s);
        return clubMemberMapper.countByExample(clubMemberExample);
    }

    @Override
    public List<ClubMember> selectClubMembersLikeName(String s, Integer getuId) {
        ClubMemberExample clubMemberExample = new ClubMemberExample();
        ClubMemberExample.Criteria criteria = clubMemberExample.createCriteria();
        criteria.andClubIdEqualTo(getuId);
        criteria.andMemNameLike(s);
        return clubMemberMapper.selectByExample(clubMemberExample);
    }

    @Override
    public ClubMember selectById(Integer mId) {
        return clubMemberMapper.selectByPrimaryKey(mId);
    }

    @Override
    public int deleteById(Integer mId) {
        return clubMemberMapper.deleteByPrimaryKey(mId);
    }
}
