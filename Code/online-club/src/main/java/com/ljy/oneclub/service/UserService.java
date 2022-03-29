package com.ljy.oneclub.service;

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
}
