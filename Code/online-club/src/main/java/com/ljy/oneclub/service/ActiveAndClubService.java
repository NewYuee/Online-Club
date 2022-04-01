package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.ActiveAndClub;
import com.ljy.oneclub.vo.ActiveAndClubVO;

import java.util.List;

public interface ActiveAndClubService {
    List<ActiveAndClub> selectOneByActiveId(Integer activeId);

    int insertOne(ActiveAndClub activeAndClub);

    List<ActiveAndClub> selectByFromUid(List<Integer> clubId);

    ActiveAndClubVO getActiveAndClubVO(int a_id);
}
