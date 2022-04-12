package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.Application;
import com.ljy.oneclub.vo.ApplicationJson;

import java.util.List;

public interface ApplicationService {
    int insertOne(Application application);

    Application selectById(int appid);

    List<Application> getApplicationByUid(int userId);

    int deleteApplicationById(int aid);

    List<Application> getSuccessOrFailApplicationByUid(int uid);

    int countNotDealApplicationByClubid(Integer getuId);

    List<ApplicationJson> getApplicationByClubId(Integer getuId);

    int countApplicationByClubId(Integer getuId);
}
