package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.ActiveAndClubMapper;
import com.ljy.oneclub.entity.ActiveAndClub;
import com.ljy.oneclub.entity.ActiveAndClubExample;
import com.ljy.oneclub.service.ActiveAndClubService;
import com.ljy.oneclub.vo.ActiveAndClubVO;
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

    @Override
    public List<ActiveAndClub> selectByFromUid(List<Integer> clubId) {
        ActiveAndClubExample activeAndClubExample = new ActiveAndClubExample();
        ActiveAndClubExample.Criteria criteria = activeAndClubExample.createCriteria();
        criteria.andFromClubIdIn(clubId);
        return activeAndClubMapper.selectByExample(activeAndClubExample);
    }

    @Override
    public ActiveAndClubVO getActiveAndClubVO(int a_id) {
        return activeAndClubMapper.getActiveAndClubVO(a_id);
    }

    @Override
    public int countByClubId(Integer getuId) {
        ActiveAndClubExample activeAndClubExample = new ActiveAndClubExample();
        ActiveAndClubExample.Criteria criteria = activeAndClubExample.createCriteria();
        criteria.andFromClubIdEqualTo(getuId);
        return activeAndClubMapper.countByExample(activeAndClubExample);
    }

    @Override
    public void deleteByAid(int a_id) {
        ActiveAndClubExample activeAndClubExample = new ActiveAndClubExample();
        ActiveAndClubExample.Criteria criteria = activeAndClubExample.createCriteria();
        criteria.andFromClubIdEqualTo(a_id);
        activeAndClubMapper.deleteByExample(activeAndClubExample);
    }
}
