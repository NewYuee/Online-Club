package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.Message;
import com.ljy.oneclub.vo.NotReadMsgVO;

import java.util.List;

public interface MessageService {
    int insertMsg(Message chatMsg);

    List<Message> selectMessagesByUidAndToUid(int uid, int toUid);

    List<Message> selectMessagesByUidAndToUidLimit5(int uid, int toUid);

    List<NotReadMsgVO> getNotReadMsgsByUid(String userId);

    Message getLastNotReadMessageByUidAndToUid(String uid, String touid);

    List<Message> getAllNotMsgsByUids(String userId, String toUserId);

    void updateMsgById(Message message);
}
