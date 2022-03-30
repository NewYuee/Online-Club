package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.CommentMapper;
import com.ljy.oneclub.entity.Comment;
import com.ljy.oneclub.entity.CommentExample;
import com.ljy.oneclub.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public int insertOneComment(Comment comment) {
        return commentMapper.insertSelective(comment);
    }

    @Override
    public List<Comment> getCommentBySourceId(int aId) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andSourceIdEqualTo(aId);
        return commentMapper.selectByExample(commentExample);
    }

    @Override
    public Comment selectCommentById(Integer replyCommentId) {
        return commentMapper.selectByPrimaryKey(replyCommentId);
    }
}
