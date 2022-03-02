package com.ljy.oneclub.dao;

import com.ljy.oneclub.entity.ClubMember;
import com.ljy.oneclub.entity.ClubMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClubMemberMapper {
    int countByExample(ClubMemberExample example);

    int deleteByExample(ClubMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClubMember record);

    int insertSelective(ClubMember record);

    List<ClubMember> selectByExample(ClubMemberExample example);

    ClubMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClubMember record, @Param("example") ClubMemberExample example);

    int updateByExample(@Param("record") ClubMember record, @Param("example") ClubMemberExample example);

    int updateByPrimaryKeySelective(ClubMember record);

    int updateByPrimaryKey(ClubMember record);
}