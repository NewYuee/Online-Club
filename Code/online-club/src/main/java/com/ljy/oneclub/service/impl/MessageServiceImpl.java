package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.MessageMapper;
import com.ljy.oneclub.entity.Message;
import com.ljy.oneclub.entity.MessageExample;
import com.ljy.oneclub.service.MessageService;
import com.ljy.oneclub.vo.NotReadMsgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;

    @Override
    public int insertMsg(Message chatMsg) {
        return messageMapper.insert(chatMsg);
    }

    @Override
    public List<Message> selectMessagesByUidAndToUid(int uid, int toUid) {
        return messageMapper.selectMessagesByUidAndToUid(uid,toUid);
    }

    @Override
    public List<Message> selectMessagesByUidAndToUidLimit5(int uid, int toUid) {
        return messageMapper.selectMessagesByUidAndToUidLimit5(uid,toUid);
    }

    @Override
    public List<NotReadMsgVO> getNotReadMsgsByUid(String userId) {
        return messageMapper.getNotReadMsgsByUid(userId);
    }

    @Override
    public Message getLastNotReadMessageByUidAndToUid(String uid, String touid) {
        return messageMapper.getLastNotReadMessageByUidAndToUid(uid,touid);
    }

    @Override
    public List<Message> getAllNotMsgsByUids(String userId, String toUserId) {
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andFromUidEqualTo(toUserId);
        criteria.andToUidEqualTo(userId);
        criteria.andIsreadEqualTo("0");
        return messageMapper.selectByExample(messageExample);
    }

    @Override
    public void updateMsgById(Message message) {
        messageMapper.updateByPrimaryKey(message);
    }
}
