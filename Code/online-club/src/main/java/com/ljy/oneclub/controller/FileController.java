package com.ljy.oneclub.controller;

import com.ljy.oneclub.msg.Msg;
import io.github.yedaxia.apidocs.ApiDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@ApiDoc
@Controller
public class FileController {

    private static final Logger logger= LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "active/file/upload",method = RequestMethod.POST)
    @ResponseBody
    public Msg uploadImgInArticle(MultipartHttpServletRequest multiRequest) throws IOException {
        logger.info("开始文件上传");

        MultipartFile multipartFile = multiRequest.getFile("file");
        System.out.println(multipartFile.getOriginalFilename());
        if (multipartFile != null) {
            String rootPath = "/root/resource/upload/article";
            //String rootPath = "E:\\Temp";
            Calendar date = Calendar.getInstance();
            File dateDirs = new File(date.get(Calendar.YEAR)
                    + File.separator + (date.get(Calendar.MONTH) + 1));
            File file = new File(rootPath + File.separator + dateDirs);
            if (!file.exists()) {
                logger.info("目录不存在，创建目录");
                file.mkdirs();
            }
            String fileName = multipartFile.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
            fileName = uuid + "_" + fileName;
            logger.info("文件：" + fileName);
            multipartFile.transferTo(new File(file, fileName));
            String path = "https://mmad.top:82/res/upload/article/"+dateDirs.toString()+"/"+fileName;
            logger.info("文章访问位置:"+path+"<\n实际存储位置："+file.toString());
            return Msg.success().addData("imgPath", path);
        }
        return Msg.fail();
    }
}
