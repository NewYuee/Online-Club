package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.UserMapper;
import com.ljy.oneclub.entity.User;
import com.ljy.oneclub.entity.UserExample;
import com.ljy.oneclub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User selectOne(User user) {
        return userMapper.selectOne(user);
    }

    @Override
    public User quryUserByName(String name) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUNameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size()==0){
            return null;
        }
        return users.get(0);
    }

    @Override
    public User quryUserByEmail(String email) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUMailAddEqualTo(email);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size()==0){
            return null;
        }
        return users.get(0);
    }

    @Override
    public int insertUser(User user) {
         return userMapper.insert(user);
    }

    @Override
    public int updatePasswordByEmail(String usermail, String encodePassword) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUMailAddEqualTo(usermail);
        List<User> users = userMapper.selectByExample(userExample);
        User user=null;
        if (users.size()==1){
            user = users.get(0);
        }
        user.setuPassword(encodePassword);
        return userMapper.updateByExample(user, userExample);
    }

    @Override
    public String queryNameById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);

        return user.getuName();
    }
}
