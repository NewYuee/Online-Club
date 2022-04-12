package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.ApplicationMapper;
import com.ljy.oneclub.entity.Application;
import com.ljy.oneclub.entity.ApplicationExample;
import com.ljy.oneclub.service.ApplicationService;
import com.ljy.oneclub.vo.ApplicationJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationMapper applicationMapper;
    @Override
    public int insertOne(Application application) {
        return applicationMapper.insert(application);
    }

    @Override
    public Application selectById(int appid) {
        return applicationMapper.selectByPrimaryKey(appid);
    }

    @Override
    public List<Application> getApplicationByUid(int userId) {
        ApplicationExample applicationExample = new ApplicationExample();
        ApplicationExample.Criteria criteria = applicationExample.createCriteria();
        criteria.andAppUserIdEqualTo(userId);
        return applicationMapper.selectByExample(applicationExample);
    }

    @Override
    public int deleteApplicationById(int aid) {
        return applicationMapper.deleteByPrimaryKey(aid);
    }

    @Override
    public List<Application> getSuccessOrFailApplicationByUid(int uid) {
        ApplicationExample applicationExample = new ApplicationExample();
        ApplicationExample.Criteria criteria = applicationExample.createCriteria();
        List<Integer> status=new ArrayList<>();
        status.add(0);
        status.add(1);
        criteria.andAppUserIdEqualTo(uid);
        criteria.andAppStatusIn(status);
        return applicationMapper.selectByExample(applicationExample);
    }

    @Override
    public int countNotDealApplicationByClubid(Integer getuId) {
        ApplicationExample applicationExample = new ApplicationExample();
        ApplicationExample.Criteria criteria = applicationExample.createCriteria();
        criteria.andAppToUserIdEqualTo(getuId);
        criteria.andAppStatusEqualTo(2);
        return applicationMapper.countByExample(applicationExample);
    }

    @Override
    public List<ApplicationJson> getApplicationByClubId(Integer getuId) {
        return applicationMapper.getApplicationByClubId(getuId);
    }

    @Override
    public int countApplicationByClubId(Integer getuId) {
        ApplicationExample applicationExample = new ApplicationExample();
        ApplicationExample.Criteria criteria = applicationExample.createCriteria();
        criteria.andAppToUserIdEqualTo(getuId);
        return applicationMapper.countByExample(applicationExample);
    }
}
