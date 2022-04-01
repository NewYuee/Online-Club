package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.vo.ActiveVO;

import java.util.List;

public interface ActiveService {

    int insertOne(Active article);

    List<Active> selectArticleByKeyWords(String content);

    Active selectById(int aId);

    void updateViewCount(Active active);

    Active selectNewActiveByUid(int uid,int type);

    List<Active> selectActiveAboutByUid(Integer getuId);

    List<ActiveVO> selectActiveVOAboutByUid(Integer getuId);
}
