package com.ljy.oneclub.utils;

import com.ljy.oneclub.entity.User;
import com.ljy.oneclub.msg.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadEnclosure {
    private static final Logger logger= LoggerFactory.getLogger(UploadEnclosure.class);
    public static String uploadImg(MultipartHttpServletRequest multiRequest) throws IOException {
        MultipartFile upload = multiRequest.getFile("file");
        String p="/root/resource/upload/enclosure/";
        if (upload.isEmpty()){
            return null;
        }
        File file=new File(p);
        //获取上传文件的名称
        String filename = upload.getOriginalFilename();
        //把文件名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().substring(0,8).replace("-", "");
        filename = uuid+filename;
        String enclosurePath="https://mmad.top:82/res/upload/enclosure/"+filename;
        System.out.println(filename);
        //完成文件上传
        upload.transferTo(new File(p,filename));
        logger.info("上传附件:"+filename);
        return enclosurePath;
    }
}
