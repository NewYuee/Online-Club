package com.ljy.oneclub.dao;

import com.ljy.oneclub.entity.Notice;
import com.ljy.oneclub.entity.NoticeExample;
import java.util.List;

import com.ljy.oneclub.vo.NoticeOfLike;
import org.apache.ibatis.annotations.Param;

public interface NoticeMapper {
    int countByExample(NoticeExample example);

    int deleteByExample(NoticeExample example);

    int deleteByPrimaryKey(Integer noticeId);

    int insert(Notice record);

    int insertSelective(Notice record);

    List<Notice> selectByExample(NoticeExample example);

    Notice selectByPrimaryKey(Integer noticeId);

    int updateByExampleSelective(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByExample(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    List<NoticeOfLike> getNoticeOfLikeByUid(@Param("uid") int uid, @Param("type") String type);

    List<NoticeOfLike> getNoticeOfComment(@Param("uid") int uid);
}