package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.Comment;

import java.util.List;

public interface CommentService {
    int insertOneComment(Comment comment);

    List<Comment> getCommentBySourceId(int aId);

    Comment selectCommentById(Integer replyCommentId);

    List<Comment> getTop2CommentBySourceId(Integer activeId);

    int getCommentCountByAid(Integer activeId);

    Comment getCommentByCid(Integer replyC_id);

    List<Comment> getCommentsByUid(int user_id);

    int deleteCommentByCid(int cid);

    Comment getCommentByPrimaryKey(Integer noticeSourceId);

    void deleteCommentBySourceId(int aid);
}
