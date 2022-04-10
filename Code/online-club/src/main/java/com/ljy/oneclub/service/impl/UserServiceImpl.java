package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.LikedRecordMapper;
import com.ljy.oneclub.dao.UserMapper;
import com.ljy.oneclub.entity.LikedRecord;
import com.ljy.oneclub.entity.LikedRecordExample;
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

    @Autowired
    LikedRecordMapper likedRecordMapper;

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

    @Override
    public int updateInfo(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<User> selectUserByKeyWords(String content) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUAuthNoEqualTo(10);
        criteria.andUNameLike("%"+content+"%");
        List<User> userList = userMapper.selectByExample(userExample);
        return userList;
    }

    @Override
    public List<User> selectClubByKeyWords(String content) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUAuthNoEqualTo(5);
        criteria.andUNameLike("%"+content+"%");
        List<User> userList = userMapper.selectByExample(userExample);
        return userList;
    }

    @Override
    public User selectUserById(Integer getuId) {
        return userMapper.selectByPrimaryKey(getuId);
    }

    @Override
    public String getNameById(Integer getuId) {
        return userMapper.getNameById(getuId);
    }

    @Override
    public int collectActive(LikedRecord likedRecord) {
        LikedRecordExample likedRecordExample = new LikedRecordExample();
        LikedRecordExample.Criteria criteria = likedRecordExample.createCriteria();
        criteria.andLikeActiveIdEqualTo(likedRecord.getLikeActiveId());
        criteria.andUIdEqualTo(likedRecord.getuId());
        List<LikedRecord> likedRecords = likedRecordMapper.selectByExample(likedRecordExample);
        if (likedRecords.size()==0){
            return likedRecordMapper.insert(likedRecord);
        }
        else
            return 0;
    }

    @Override
    public List<User> getAllUserByAid(int i) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUAuthNoEqualTo(i);
        return userMapper.selectByExample(userExample);
    }

    @Override
    public void deleteByUid(int userId) {
        userMapper.deleteByPrimaryKey(userId);
    }
}
