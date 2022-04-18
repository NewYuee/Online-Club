package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.Notice;
import com.ljy.oneclub.vo.NoticeOfLike;

import java.util.List;

public interface NoticeService {
    int insertOne(Notice notice);

    int deleteRecordByUidAndAid(Integer getuId, Integer sourceId);

    List<NoticeOfLike> getNoticeOfLikeByUid(int uid, String type);

    List<NoticeOfLike> getNoticeOfComment(int uid);

    void deleteNoticeBySourceId(int aid);
}
