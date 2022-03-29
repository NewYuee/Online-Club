package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.Active;

import java.util.List;

public interface ActiveService {

    int insertOne(Active article);

    List<Active> selectArticleByKeyWords(String content);

    Active selectById(int aId);

    void updateViewCount(Active active);
}
