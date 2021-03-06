package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.LikedRecord;
import com.ljy.oneclub.entity.User;

import java.util.List;

public interface UserService {
    User selectOne(User user);

    User quryUserByName(String name);

    User quryUserByEmail(String email);

    int insertUser(User user);

    int updatePasswordByEmail(String usermail, String encodePassword);

    String queryNameById(Integer clubId);

    int updateInfo(User user);

    List<User> selectUserByKeyWords(String content);

    List<User> selectClubByKeyWords(String content);

    User selectUserById(Integer getuId);

    String getNameById(Integer getuId);

    int collectActive(LikedRecord likedRecord);

    List<User> getAllUserByAid(int i);

    void deleteByUid(int userId);

    int countByAid(int i);

    List<User> getAllUserByAidAndKeyWord(int i, String keyword);

    int countByAidAndKeyWord(int i, String keyword);

    int updateClubInfo(User club);
}
