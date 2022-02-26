package com.ljy.oneclub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileController {

    private static final Logger logger= LoggerFactory.getLogger(FileController.class);

    @RequestMapping("file/upload")
    public String uploadFile(MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        logger.info("开始文件上传");
        String path="F:\\A毕业设计/基于SSM的学生社团系统的设计与实现/ab/c";
        File file=new File(path);
        if (!file.exists()){
            logger.info("目录："+path+"不存在，创建目录");
            file.mkdir();
        }
        String fileName=multipartFile.getOriginalFilename();
        String uuid= UUID.randomUUID().toString();
        fileName=uuid+fileName;
        logger.info("文件："+fileName+"上传路径为："+path);
        multipartFile.transferTo(new File(path,fileName));
        return "success";
    }
}
