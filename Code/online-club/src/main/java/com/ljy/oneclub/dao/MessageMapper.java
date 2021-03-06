package com.ljy.oneclub.dao;

import com.ljy.oneclub.entity.Message;
import com.ljy.oneclub.entity.MessageExample;
import java.util.List;

import com.ljy.oneclub.vo.NotReadMsgVO;
import org.apache.ibatis.annotations.Param;

public interface MessageMapper {
    int countByExample(MessageExample example);

    int deleteByExample(MessageExample example);

    int deleteByPrimaryKey(Integer mId);

    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByExampleWithBLOBs(MessageExample example);

    List<Message> selectByExample(MessageExample example);

    Message selectByPrimaryKey(Integer mId);

    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExampleWithBLOBs(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);

    List<Message> selectMessagesByUidAndToUid(@Param("uid") int uid, @Param("toUid") int toUid);
    List<Message> selectMessagesByUidAndToUidLimit5(@Param("uid") int uid, @Param("toUid") int toUid);

    List<NotReadMsgVO> getNotReadMsgsByUid(@Param("uid") String userId);

    Message getLastNotReadMessageByUidAndToUid(@Param("uid")String uid, @Param("touid")String touid);
}