package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.NoticeMapper;
import com.ljy.oneclub.entity.Notice;
import com.ljy.oneclub.entity.NoticeExample;
import com.ljy.oneclub.service.NoticeService;
import com.ljy.oneclub.vo.NoticeOfLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<NoticeOfLike> getNoticeOfLikeByUid(int uid, String type) {
        return noticeMapper.getNoticeOfLikeByUid(uid, type);
    }

    @Override
    public List<NoticeOfLike> getNoticeOfComment(int uid) {
        return noticeMapper.getNoticeOfComment(uid);
    }

    @Override
    public void deleteNoticeBySourceId(int aid) {
        NoticeExample noticeExample = new NoticeExample();
        NoticeExample.Criteria criteria = noticeExample.createCriteria();
        criteria.andNoticeSourceIdEqualTo(aid);
        noticeMapper.deleteByExample(noticeExample);
    }
}
