package com.ljy.oneclub.dao;

import com.ljy.oneclub.entity.Application;
import com.ljy.oneclub.entity.ApplicationExample;
import java.util.List;

import com.ljy.oneclub.vo.ApplicationJson;
import org.apache.ibatis.annotations.Param;

public interface ApplicationMapper {
    int countByExample(ApplicationExample example);

    int deleteByExample(ApplicationExample example);

    int deleteByPrimaryKey(Integer appId);

    int insert(Application record);

    int insertSelective(Application record);

    List<Application> selectByExample(ApplicationExample example);

    Application selectByPrimaryKey(Integer appId);

    int updateByExampleSelective(@Param("record") Application record, @Param("example") ApplicationExample example);

    int updateByExample(@Param("record") Application record, @Param("example") ApplicationExample example);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);

    List<ApplicationJson> getApplicationByClubId(@Param("clubId") Integer getuId);
}