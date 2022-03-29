package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.ApplicationMapper;
import com.ljy.oneclub.entity.Application;
import com.ljy.oneclub.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationMapper applicationMapper;
    @Override
    public int insertOne(Application application) {
        return applicationMapper.insert(application);
    }
}
