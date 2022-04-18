package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.ClubMember;
import com.ljy.oneclub.vo.HotClubVO;

import java.util.List;

public interface ClubMemberService {

    List<ClubMember> selectMyClubByUid(Integer getuId);

    int countMembershipByClubId(Integer getuId);

    int insertClubMember(ClubMember clubMember);

    int deleteClubMemberByUidAndClubId(Integer appUserId, Integer appToUserId);

    List<ClubMember> selectMemberByClubId(Integer getuId);

    int countClubMembersLikeName(String s,Integer clubId);

    List<ClubMember> selectClubMembersLikeName(String s, Integer getuId);

    ClubMember selectById(Integer mId);

    int deleteById(Integer mId);

    List<HotClubVO> getIndexClubInfo();
}
