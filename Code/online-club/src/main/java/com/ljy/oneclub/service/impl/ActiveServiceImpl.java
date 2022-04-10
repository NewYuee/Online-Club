package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.ActiveMapper;
import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.entity.ActiveExample;
import com.ljy.oneclub.service.ActiveService;
import com.ljy.oneclub.vo.ActiveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    ActiveMapper activeMapper;

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
}
