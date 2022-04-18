package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.LikedRecordMapper;
import com.ljy.oneclub.entity.LikedRecord;
import com.ljy.oneclub.entity.LikedRecordExample;
import com.ljy.oneclub.service.LikeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeRecordServiceImpl implements LikeRecordService {

    @Autowired
    LikedRecordMapper likedRecordMapper;

    @Override
    public List<LikedRecord> selectByActiveIdAndUid(Integer sourceId, Integer getuId) {
        LikedRecordExample likedRecordExample = new LikedRecordExample();
        LikedRecordExample.Criteria criteria = likedRecordExample.createCriteria();
        criteria.andUIdEqualTo(getuId);
        criteria.andLikeActiveIdEqualTo(sourceId);
        return likedRecordMapper.selectByExample(likedRecordExample);
    }

    @Override
    public int uncollectActiveByActiveIdAndUid(Integer sourceId, Integer getuId) {
        LikedRecordExample likedRecordExample = new LikedRecordExample();
        LikedRecordExample.Criteria criteria = likedRecordExample.createCriteria();
        criteria.andUIdEqualTo(getuId);
        criteria.andLikeActiveIdEqualTo(sourceId);
        return likedRecordMapper.deleteByExample(likedRecordExample);
    }

    @Override
    public int getCountByActiveId(int aid) {
        LikedRecordExample likedRecordExample = new LikedRecordExample();
        LikedRecordExample.Criteria criteria = likedRecordExample.createCriteria();
        criteria.andLikeActiveIdEqualTo(aid);
        return likedRecordMapper.countByExample(likedRecordExample);
    }

    @Override
    public void deleteBySourceId(int aid) {
        LikedRecordExample likedRecordExample = new LikedRecordExample();
        LikedRecordExample.Criteria criteria = likedRecordExample.createCriteria();
        criteria.andLikeActiveIdEqualTo(aid);
        likedRecordMapper.deleteByExample(likedRecordExample);
    }
}
