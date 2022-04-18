package com.ljy.oneclub.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class RecentWeekTimeUtil {


    //得到今天以及之前的六天的日期列表
    public static List<Date> dateToWeek(Date mdate) {
        Date fdate;
        List<Date> list = new ArrayList<>();
        Long fTime = mdate.getTime() - 7 * 24 * 3600000; //获取时间戳
        System.out.println();
        for (int a = 1; a <= 7; a++) {
            fdate = new Date();
            fdate.setTime(fTime + (a * 24 * 3600000));
            list.add(a - 1, fdate);
        }
        return list;
    }



    //根据日期列表将周几和日期一一对应如：周一，2022-04-04
    public static LinkedHashMap<String,String> getWeekAndDate(List<Date> days){
        // 定义输出日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEE");
        //周几-日期的一一对应
        LinkedHashMap<String,String> weekMap=new LinkedHashMap<>();
        for (Date date : days) {
            String time=sdf.format(date);
            String[] s = time.split(" ");
            weekMap.put(s[1],s[0]);
        }
        //返回有序map
        return weekMap;
    }

}
