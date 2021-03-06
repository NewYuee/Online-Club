package com.ljy.oneclub.dao;

import com.ljy.oneclub.entity.Comment;
import com.ljy.oneclub.entity.CommentExample;
import java.util.List;

import com.ljy.oneclub.vo.CommentJson;
import org.apache.ibatis.annotations.Param;

public interface CommentMapper {
    int countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> getTop2CommentBySourceId(@Param("aid") Integer activeId);

    List<CommentJson> getCommentByKeyword(@Param("keyword") String keyword);

    List<CommentJson> getAllCommentJson();

    int countComment();
}