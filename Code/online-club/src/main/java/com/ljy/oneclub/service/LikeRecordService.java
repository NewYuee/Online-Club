package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.LikedRecord;

import java.util.List;

public interface LikeRecordService {
    List<LikedRecord> selectByActiveIdAndUid(Integer sourceId, Integer getuId);

    int uncollectActiveByActiveIdAndUid(Integer sourceId, Integer getuId);

    int getCountByActiveId(int aid);
}
