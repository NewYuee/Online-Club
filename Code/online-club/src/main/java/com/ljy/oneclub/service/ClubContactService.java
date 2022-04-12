package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.ClubContact;

import java.util.List;

public interface ClubContactService {
    List<ClubContact> getContactInfoByClubId(Integer clubId);

    int countByClubId(Integer clubId);

    List<ClubContact> getContactInfoByClubIdAndKeyword(Integer clubId, String keyword);

    int countByClubIdAndKeyword(Integer clubId, String keyword);

    List<ClubContact> getAllContactInfo();

    int countAll();

    List<ClubContact> getContactInfoByKeyword(String keyword);

    int countByKeyword(String keyword);
}
