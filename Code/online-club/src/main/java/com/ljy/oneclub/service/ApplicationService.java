package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.Application;

import java.util.List;

public interface ApplicationService {
    int insertOne(Application application);

    Application selectById(int appid);

    List<Application> getApplicationByUid(int userId);

    int deleteApplicationById(int aid);
}
