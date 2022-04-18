package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.Active;
import com.ljy.oneclub.vo.ActiveJson;
import com.ljy.oneclub.vo.ActiveOneDayInfo;
import com.ljy.oneclub.vo.ActiveVO;
import com.ljy.oneclub.vo.DayActiveVO;

import java.util.Date;
import java.util.List;

public interface ActiveService {

    int insertOne(Active article);

    List<Active> selectArticleByKeyWords(String content);

    Active selectById(int aId);

    void updateViewCount(Active active);

    Active selectNewActiveByUid(int uid,int type);

    List<Active> selectActiveAboutByUid(Integer getuId);

    List<ActiveVO> selectActiveVOAboutByUid(Integer getuId);

    List<ActiveVO> selectHomePageActiveByUid(Integer getuId);

    int deleteActiveByAid(int aid);

    List<ActiveVO> selectLikeArticleByUid(int uid);

    int countActiveByUid(Integer getuId);

    void deleteActiveByUid(int userId);

    int countByPrimaryKey();

    DayActiveVO countActiveByDayDate(String time);

    List<ActiveJson> selectAllActive();

    DayActiveVO countDayActiveByClubIdAndDateTime(Integer getuId, String timeStr);

    List<ActiveJson> selectAllActiveByClubId(Integer getuId);

    List<ActiveOneDayInfo> getAfterOneDayActiveByType(int type, String time);

    //根据类型和时间获取站内当天的活动计数
    DayActiveVO countOneDayActiveByType(int i, String dateStr);

    DayActiveVO countOneClubActiveOneTimeByType(int i, String dateStr, Integer getuId);

    List<Active> getOneClubActiveAfterOneTime( String s, int clubId);
}
