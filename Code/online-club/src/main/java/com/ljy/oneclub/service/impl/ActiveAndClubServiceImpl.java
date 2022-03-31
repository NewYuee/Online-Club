package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.ActiveAndClubMapper;
import com.ljy.oneclub.entity.ActiveAndClub;
import com.ljy.oneclub.entity.ActiveAndClubExample;
import com.ljy.oneclub.service.ActiveAndClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveAndClubServiceImpl implements ActiveAndClubService {

    @Autowired
    ActiveAndClubMapper activeAndClubMapper;

    @Override
    public List<ActiveAndClub> selectOneByActiveId(Integer activeId) {
        ActiveAndClubExample activeAndClubExample = new ActiveAndClubExample();
        ActiveAndClubExample.Criteria criteria = activeAndClubExample.createCriteria();
        criteria.andActiveIdEqualTo(activeId);
        return activeAndClubMapper.selectByExample(activeAndClubExample);
    }

    @Override
    public int insertOne(ActiveAndClub activeAndClub) {
        return activeAndClubMapper.insert(activeAndClub);
    }
}
