package com.ljy.oneclub.dao;

import com.ljy.oneclub.entity.LikedRecord;
import com.ljy.oneclub.entity.LikedRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LikedRecordMapper {
    int countByExample(LikedRecordExample example);

    int deleteByExample(LikedRecordExample example);

    int deleteByPrimaryKey(Integer likeId);

    int insert(LikedRecord record);

    int insertSelective(LikedRecord record);

    List<LikedRecord> selectByExample(LikedRecordExample example);

    LikedRecord selectByPrimaryKey(Integer likeId);

    int updateByExampleSelective(@Param("record") LikedRecord record, @Param("example") LikedRecordExample example);

    int updateByExample(@Param("record") LikedRecord record, @Param("example") LikedRecordExample example);

    int updateByPrimaryKeySelective(LikedRecord record);

    int updateByPrimaryKey(LikedRecord record);
}