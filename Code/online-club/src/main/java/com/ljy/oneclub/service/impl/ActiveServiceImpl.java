package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.ActiveMapper;
import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    ActiveMapper activeMapper;

    @Override
    public int insertOne(Active article) {
        return activeMapper.insert(article);
    }
}
