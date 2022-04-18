package com.ljy.oneclub.utils;

import org.junit.Test;
import org.springframework.util.DigestUtils;

public class MD5Test {

    @Test
    public void testMD5(){
        String testStr="字符串A";
        System.out.println("\n加密前的字符串为："+testStr);
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(testStr.getBytes());
        System.out.println(testStr+"进行MD5加密后字符串为：\n"+md5DigestAsHex);
    }

}
