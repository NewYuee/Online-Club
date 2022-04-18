package com.ljy.oneclub.dao;

import com.ljy.oneclub.entity.ClubContact;
import com.ljy.oneclub.entity.ClubContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClubContactMapper {
    int countByExample(ClubContactExample example);

    int deleteByExample(ClubContactExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClubContact record);

    int insertSelective(ClubContact record);

    List<ClubContact> selectByExample(ClubContactExample example);

    ClubContact selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClubContact record, @Param("example") ClubContactExample example);

    int updateByExample(@Param("record") ClubContact record, @Param("example") ClubContactExample example);

    int updateByPrimaryKeySelective(ClubContact record);

    int updateByPrimaryKey(ClubContact record);

    List<ClubContact> getAllContactInfo();

    int countAll();
}