package com.ljy.oneclub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

public class TestMap {
    @Test
    public void testMap(){
        ConcurrentHashMap<String, String> Map = new ConcurrentHashMap<>();
        String s="{\"name\":\"a\",\"pwd\":\"123\"}";
        JSONObject jsonObject = JSON.parseObject(s);

        System.out.println(jsonObject.getString("name"));
        Map.put("A","AisA");
        Map.put("B","BisB");
        Map.put("A","a");
        System.out.println("map size="+Map.size()+";A="+Map.get("A")+";B="+Map.get("B"));
    }
}
