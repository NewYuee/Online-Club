package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.ClubMember;

import java.util.List;

public interface ClubMemberService {

    List<ClubMember> selectMyClubByUid(Integer getuId);

    int countMembershipByClubId(Integer getuId);
}
