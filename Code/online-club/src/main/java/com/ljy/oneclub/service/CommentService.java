package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.Comment;

import java.util.List;

public interface CommentService {
    int insertOneComment(Comment comment);

    List<Comment> getCommentBySourceId(int aId);

    Comment selectCommentById(Integer replyCommentId);
}
