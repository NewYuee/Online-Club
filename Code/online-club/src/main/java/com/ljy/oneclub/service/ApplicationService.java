package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.Application;

public interface ApplicationService {
    int insertOne(Application application);

    Application selectById(int appid);
}
