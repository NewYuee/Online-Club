package com.ljy.oneclub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljy.oneclub.dao.UserMapper;
import com.ljy.oneclub.entity.User;
import com.ljy.oneclub.entity.UserExample;
import com.ljy.oneclub.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestMap {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;


    @Test
    public void testMap() throws ParseException {
        ConcurrentHashMap<String, String> Map = new ConcurrentHashMap<>();
        String s="{\"name\":\"  \",\"pwd\":\"123\"}";
        JSONObject jsonObject = JSON.parseObject(s);
        if (jsonObject.getString("name").replace(" ","").length()!=0){
        System.out.println("name=>"+jsonObject.getString("name").length());}
        Map.put("A","AisA");
        Map.put("B","BisB");
        Map.put("A","a");
        System.out.println("map size="+Map.size()+";A="+Map.get("A")+";B="+Map.get("B"));
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject json=new JSONObject();
        json.put("valid",true);
        System.out.println(json.get("valid"));


    }

    @Test
    public void testUser(){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUNameEqualTo("tests");
        criteria.andUPasswordEqualTo("123456");
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size()!=0){
            System.out.println(users.size());
        }
        else
            System.out.println("nullInfo");
    }

    @Test
    public void mkdirTest(){
        String path="F:\\A毕业设计\\基于SSM的学生社团系统的设计与实现\\ab\\cd";
        File file=new File(path);
        if (!file.exists()){
            file.mkdirs();
            System.out.println("mkdir:"+path);
        }
        String ss=File.separator;
        System.out.println(ss);

    }
}
