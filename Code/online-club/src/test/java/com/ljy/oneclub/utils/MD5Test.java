package com.ljy.oneclub.utils;

import org.junit.Test;
import org.springframework.util.DigestUtils;

public class MD5Test {

    @Test
    public void testMD5(){
        String password="123456";
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println("一般md5加密后密码:"+md5DigestAsHex);
        String slat="asc2燕窝";
        String slat11="asc2燕窝";
        password+=slat;
        System.out.println("数组密码加密:"+DigestUtils.md5DigestAsHex(slat11.getBytes()));
        int[] arr={1,2,3,4,5,6};
        System.out.println("数组密码加密:"+DigestUtils.md5DigestAsHex(slat.getBytes()));
        //RandomValidateCodeUtil randomValidateCodeUtil = new RandomValidateCodeUtil();
        //String randomString = randomValidateCodeUtil.getRandomString(3);
        //System.out.println(randomString);


    }

}
