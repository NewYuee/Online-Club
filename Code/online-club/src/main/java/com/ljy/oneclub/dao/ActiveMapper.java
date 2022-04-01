package com.ljy.oneclub.dao;

import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.entity.ActiveExample;
import java.util.List;

import com.ljy.oneclub.vo.ActiveVO;
import org.apache.ibatis.annotations.Param;

public interface ActiveMapper {
    int countByExample(ActiveExample example);

    int deleteByExample(ActiveExample example);

    int deleteByPrimaryKey(Integer activeId);

    int insert(Active record);

    int insertSelective(Active record);

    List<Active> selectByExampleWithBLOBs(ActiveExample example);

    List<Active> selectByExample(ActiveExample example);

    Active selectByPrimaryKey(Integer activeId);

    int updateByExampleSelective(@Param("record") Active record, @Param("example") ActiveExample example);

    int updateByExampleWithBLOBs(@Param("record") Active record, @Param("example") ActiveExample example);

    int updateByExample(@Param("record") Active record, @Param("example") ActiveExample example);

    int updateByPrimaryKeySelective(Active record);

    int updateByPrimaryKeyWithBLOBs(Active record);

    int updateByPrimaryKey(Active record);

    Active selectNewActiveByUid(@Param("uid") Integer uid,@Param("type") Integer type);

    List<Active> selectActiveAboutByUid(@Param("uid") Integer getuId);

    List<ActiveVO> selectActiveVOAboutByUid(@Param("uid") Integer getuId);
}