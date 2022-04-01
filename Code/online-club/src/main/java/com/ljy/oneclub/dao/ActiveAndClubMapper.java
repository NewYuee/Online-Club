package com.ljy.oneclub.dao;

import com.ljy.oneclub.entity.ActiveAndClub;
import com.ljy.oneclub.entity.ActiveAndClubExample;
import java.util.List;

import com.ljy.oneclub.vo.ActiveAndClubVO;
import org.apache.ibatis.annotations.Param;

public interface ActiveAndClubMapper {
    int countByExample(ActiveAndClubExample example);

    int deleteByExample(ActiveAndClubExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActiveAndClub record);

    int insertSelective(ActiveAndClub record);

    List<ActiveAndClub> selectByExample(ActiveAndClubExample example);

    ActiveAndClub selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActiveAndClub record, @Param("example") ActiveAndClubExample example);

    int updateByExample(@Param("record") ActiveAndClub record, @Param("example") ActiveAndClubExample example);

    int updateByPrimaryKeySelective(ActiveAndClub record);

    int updateByPrimaryKey(ActiveAndClub record);

    ActiveAndClubVO getActiveAndClubVO(@Param("aid") int a_id);
}