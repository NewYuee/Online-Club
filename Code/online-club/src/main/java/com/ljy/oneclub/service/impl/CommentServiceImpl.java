package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.CommentMapper;
import com.ljy.oneclub.entity.Comment;
import com.ljy.oneclub.entity.CommentExample;
import com.ljy.oneclub.service.CommentService;
import com.ljy.oneclub.vo.CommentJson;
import com.ljy.oneclub.vo.CommentVO;
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

    @Override
    public List<Comment> getTop2CommentBySourceId(Integer activeId) {
        return commentMapper.getTop2CommentBySourceId(activeId);
    }

    @Override
    public int getCommentCountByAid(Integer activeId) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andSourceIdEqualTo(activeId);
        return commentMapper.countByExample(commentExample);
    }

    @Override
    public Comment getCommentByCid(Integer replyC_id) {
        return commentMapper.selectByPrimaryKey(replyC_id);
    }

    @Override
    public List<Comment> getCommentsByUid(int user_id) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andUIdEqualTo(user_id);
        return commentMapper.selectByExample(commentExample);
    }

    @Override
    public int deleteCommentByCid(int cid) {
        //删除回复该评论的其它评论
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andReplyCommentIdEqualTo(cid);
        commentMapper.deleteByExample(commentExample);
        //再删除本条评论
        return commentMapper.deleteByPrimaryKey(cid);
    }

    @Override
    public Comment getCommentByPrimaryKey(Integer noticeSourceId) {
        return commentMapper.selectByPrimaryKey(noticeSourceId);
    }

    @Override
    public void deleteCommentBySourceId(int aid) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andSourceIdEqualTo(aid);
        commentMapper.deleteByExample(commentExample);
    }

    @Override
    public void deleteCommentByUid(int userId) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andUIdEqualTo(userId);
        commentMapper.deleteByExample(commentExample);
    }

    @Override
    public int countComment() {
        return commentMapper.countComment();
    }

    @Override
    public int countCommentByKeyword(String keyword) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andCommentContentLike("%"+keyword+"%");
        return commentMapper.countByExample(commentExample);
    }

    @Override
    public List<CommentJson> getCommentByKeyword(String keyword) {
        return commentMapper.getCommentByKeyword(keyword);
    }

    @Override
    public List<CommentJson> getAllCommentJson() {
        return commentMapper.getAllCommentJson();
    }
}
