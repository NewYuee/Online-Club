package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.ClubContactMapper;
import com.ljy.oneclub.entity.ClubContact;
import com.ljy.oneclub.entity.ClubContactExample;
import com.ljy.oneclub.service.ClubContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubContactImpl implements ClubContactService {
    @Autowired
    ClubContactMapper clubContactMapper;

    @Override
    public List<ClubContact> getContactInfoByClubId(Integer clubId) {
        ClubContactExample clubContactExample = new ClubContactExample();
        ClubContactExample.Criteria criteria = clubContactExample.createCriteria();
        criteria.andClubIdEqualTo(clubId);
        return clubContactMapper.selectByExample(clubContactExample);
    }

    @Override
    public int countByClubId(Integer clubId) {
        ClubContactExample clubContactExample = new ClubContactExample();
        ClubContactExample.Criteria criteria = clubContactExample.createCriteria();
        criteria.andClubIdEqualTo(clubId);
        return clubContactMapper.countByExample(clubContactExample);
    }

    @Override
    public List<ClubContact> getContactInfoByClubIdAndKeyword(Integer clubId, String keyword) {
        ClubContactExample clubContactExample = new ClubContactExample();
        ClubContactExample.Criteria criteria = clubContactExample.createCriteria();
        criteria.andClubIdEqualTo(clubId);
        criteria.andRealNameLike("%"+keyword+"%");
        return clubContactMapper.selectByExample(clubContactExample);
    }

    @Override
    public int countByClubIdAndKeyword(Integer clubId, String keyword) {
        ClubContactExample clubContactExample = new ClubContactExample();
        ClubContactExample.Criteria criteria = clubContactExample.createCriteria();
        criteria.andClubIdEqualTo(clubId);
        criteria.andRealNameLike("%"+keyword+"%");
        return clubContactMapper.countByExample(clubContactExample);
    }

    @Override
    public List<ClubContact> getAllContactInfo() {
        return clubContactMapper.getAllContactInfo();
    }

    @Override
    public int countAll() {
        return clubContactMapper.countAll();
    }

    @Override
    public List<ClubContact> getContactInfoByKeyword(String keyword) {
        ClubContactExample clubContactExample = new ClubContactExample();
        ClubContactExample.Criteria criteria = clubContactExample.createCriteria();
        criteria.andRealNameLike("%"+keyword+"%");
        return clubContactMapper.selectByExample(clubContactExample);
    }

    @Override
    public int countByKeyword(String keyword) {
        ClubContactExample clubContactExample = new ClubContactExample();
        ClubContactExample.Criteria criteria = clubContactExample.createCriteria();
        criteria.andRealNameLike("%"+keyword+"%");
        return clubContactMapper.countByExample(clubContactExample);
    }

    @Override
    public int insertOne(ClubContact clubContact) {
        return clubContactMapper.insertSelective(clubContact);
    }
}
