package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.User;

public interface UserService {
    User selectOne(User user);

    User quryUserByName(String name);

    User quryUserByEmail(String email);

    int insertUser(User user);

    int updatePasswordByEmail(String usermail, String encodePassword);

    String queryNameById(Integer clubId);

    int updateInfo(User user);
}
