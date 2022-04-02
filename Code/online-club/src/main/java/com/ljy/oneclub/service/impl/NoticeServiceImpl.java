package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.NoticeMapper;
import com.ljy.oneclub.entity.Notice;
import com.ljy.oneclub.entity.NoticeExample;
import com.ljy.oneclub.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public int insertOne(Notice notice) {
        return noticeMapper.insert(notice);
    }

    @Override
    public int deleteRecordByUidAndAid(Integer getuId, Integer sourceId) {
        NoticeExample noticeExample = new NoticeExample();
        NoticeExample.Criteria criteria = noticeExample.createCriteria();
        criteria.andNoticeSourceIdEqualTo(sourceId);
        criteria.andNoticeUserIdEqualTo(getuId);
        return noticeMapper.deleteByExample(noticeExample);
    }
}
