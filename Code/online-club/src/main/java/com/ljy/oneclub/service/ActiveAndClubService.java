package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.ActiveAndClub;

import java.util.List;

public interface ActiveAndClubService {
    List<ActiveAndClub> selectOneByActiveId(Integer activeId);

    int insertOne(ActiveAndClub activeAndClub);
}
