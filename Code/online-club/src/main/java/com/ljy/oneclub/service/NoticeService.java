package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.Notice;

public interface NoticeService {
    int insertOne(Notice notice);

    int deleteRecordByUidAndAid(Integer getuId, Integer sourceId);
}
