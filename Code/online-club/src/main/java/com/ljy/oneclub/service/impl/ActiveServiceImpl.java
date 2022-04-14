package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.ActiveAndClubMapper;
import com.ljy.oneclub.dao.ActiveMapper;
import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.entity.ActiveAndClubExample;
import com.ljy.oneclub.entity.ActiveExample;
import com.ljy.oneclub.service.ActiveService;
import com.ljy.oneclub.vo.ActiveJson;
import com.ljy.oneclub.vo.ActiveVO;
import com.ljy.oneclub.vo.DayActiveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    ActiveMapper activeMapper;
    @Autowired
    ActiveAndClubMapper activeAndClubMapper;

    @Override
    public int insertOne(Active article) {
        return activeMapper.insert(article);
    }

    @Override
    public List<Active> selectArticleByKeyWords(String content) {
        ActiveExample activeExample = new ActiveExample();
        ActiveExample.Criteria criteria = activeExample.createCriteria();
        criteria.andActiveTitleLike("%"+content+"%");
        criteria.andActiveTypeEqualTo(50);
        return activeMapper.selectByExample(activeExample);
    }

    @Override
    public Active selectById(int aId) {
        return activeMapper.selectByPrimaryKey(aId);
    }

    @Override
    public void updateViewCount(Active active) {
        ActiveExample activeExample = new ActiveExample();
        ActiveExample.Criteria criteria = activeExample.createCriteria();
        criteria.andActiveIdEqualTo(active.getActiveId());
        activeMapper.updateByExampleSelective(active,activeExample);
    }

    @Override
    public Active selectNewActiveByUid(int uid,int type) {
        return activeMapper.selectNewActiveByUid(uid,type);
    }

    @Override
    public List<Active> selectActiveAboutByUid(Integer getuId) {
        return activeMapper.selectActiveAboutByUid(getuId);
    }

    @Override
    public List<ActiveVO> selectActiveVOAboutByUid(Integer getuId) {
        return activeMapper.selectActiveVOAboutByUid(getuId);
    }

    @Override
    public List<ActiveVO> selectHomePageActiveByUid(Integer getuId) {
        return activeMapper.selectHomePageActiveByUid(getuId);
    }

    @Override
    public int deleteActiveByAid(int aid) {
        ActiveAndClubExample activeAndClubExample = new ActiveAndClubExample();
        ActiveAndClubExample.Criteria criteria = activeAndClubExample.createCriteria();
        criteria.andActiveIdEqualTo(aid);
        activeAndClubMapper.deleteByExample(activeAndClubExample);
        return activeMapper.deleteByPrimaryKey(aid);
    }

    @Override
    public List<ActiveVO> selectLikeArticleByUid(int uid) {
        return activeMapper.selectLikeArticleByUid(uid);
    }

    @Override
    public int countActiveByUid(Integer getuId) {
        ActiveExample activeExample = new ActiveExample();
        ActiveExample.Criteria criteria = activeExample.createCriteria();
        criteria.andActiveIdEqualTo(getuId);
        return activeMapper.countByExample(activeExample);
    }

    @Override
    public void deleteActiveByUid(int userId) {
        ActiveExample activeExample = new ActiveExample();
        ActiveExample.Criteria criteria = activeExample.createCriteria();
        criteria.andUIdEqualTo(userId);
        activeMapper.deleteByExample(activeExample);
    }

    @Override
    public int countByPrimaryKey() {
        return activeMapper.countActive();
    }

    @Override
    public DayActiveVO countActiveByDayDate(String time) {
        return activeMapper.countActiveByDayDate(time);
    }

    @Override
    public List<ActiveJson> selectAllActive() {
        return activeMapper.selectAllActive();
    }

    @Override
    public DayActiveVO countDayActiveByClubIdAndDateTime(Integer getuId, String timeStr) {
        return activeMapper.countDayActiveByClubIdAndDateTime(getuId,timeStr);
    }

    @Override
    public List<ActiveJson> selectAllActiveByClubId(Integer getuId) {
        return activeMapper.selectAllActiveByClubId(getuId);
    }


}
