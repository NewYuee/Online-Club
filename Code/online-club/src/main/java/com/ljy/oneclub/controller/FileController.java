package com.ljy.oneclub.controller;

import com.ljy.oneclub.entity.User;
import com.ljy.oneclub.msg.Msg;
import com.ljy.oneclub.service.UserService;
import io.github.yedaxia.apidocs.ApiDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


@Controller
public class FileController {

    private static final Logger logger= LoggerFactory.getLogger(FileController.class);

    /**
     * 针对富文本框处理图片上传和回调
     * @param multiRequest 文件流
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "active/file/upload",method = RequestMethod.POST)
    @ResponseBody
    public Msg uploadImgInArticle(MultipartHttpServletRequest multiRequest) throws IOException {
        logger.info("开始文件上传");

        MultipartFile multipartFile = multiRequest.getFile("file");
        System.out.println(multipartFile.getOriginalFilename());
        if (multipartFile != null) {
            String rootPath = "/root/resource/upload/article";
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

    @Autowired
    UserService userService;

    /**
     * 上传头像
     * @param session
     * @param multiRequest
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "upload/avatar",method =RequestMethod.POST )
    @ResponseBody
    public Msg uploadAvatar(HttpSession session,MultipartHttpServletRequest multiRequest) throws IOException {
        MultipartFile upload = multiRequest.getFile("file");
        String p="/root/resource/avatar/";
        if (upload.isEmpty()){
            return Msg.fail();
        }
        //判断，该路径是否存在
        File file=new File(p);
        if (!file.exists()) {
            //创建文件夹
            file.mkdirs();
        }
        //因为配好了解释器所以不用再进行解析
        //说明上传文件项
        //获取上传文件的名称
        String filename = upload.getOriginalFilename();
        //把文件名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().substring(0,8).replace("-", "");
        filename = uuid+filename;
        String avatarPath="https://mmad.top:82/res/avatar/"+filename;
        User user = (User)session.getAttribute("userInfo");
        if (user!=null){
            user.setuProfilePhotoName(avatarPath);
            userService.updateInfo(user);
            session.removeAttribute("userInfo");
            session.setAttribute("userInfo",user);
            //完成文件上传
            upload.transferTo(new File(p,filename));
            return Msg.success();
        }
        User admin = (User)session.getAttribute("admin");
        if (admin!=null){
            admin.setuProfilePhotoName(avatarPath);
            userService.updateInfo(admin);
            session.removeAttribute("admin");
            session.setAttribute("admin",admin);
            //完成文件上传
            upload.transferTo(new File(p,filename));
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 更新背景图
     * @param session
     * @param multiRequest
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "upload/bkimg",method = RequestMethod.POST)
    @ResponseBody
    public Msg uploadBkImg(HttpSession session,MultipartHttpServletRequest multiRequest) throws IOException {
        MultipartFile upload = multiRequest.getFile("file");
        String p="/root/resource/bkImg/";
        if (upload.isEmpty()){
            return Msg.fail();
        }
        //判断，该路径是否存在
        File file=new File(p);
        //获取上传文件的名称
        String filename = upload.getOriginalFilename();
        //把文件名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().substring(0,8).replace("-", "");
        filename = uuid+filename;
        String bkImgPath="https://mmad.top:82/res/bkImg/"+filename;
        User user = (User)session.getAttribute("userInfo");
        if (user!=null){
            user.setuProfileBackgroundimgName(bkImgPath);
            userService.updateInfo(user);
            session.removeAttribute("userInfo");
            session.setAttribute("userInfo",user);
            //完成文件上传
            upload.transferTo(new File(p,filename));
            logger.info("用户:"+user.getuName()+"更新了背景图"+filename);
            return Msg.success();
        }
        User admin = (User)session.getAttribute("admin");
        if (admin!=null){
            admin.setuProfileBackgroundimgName(bkImgPath);
            userService.updateInfo(admin);
            session.removeAttribute("admin");
            session.setAttribute("admin",admin);
            //完成文件上传
            upload.transferTo(new File(p,filename));
            logger.info("社团管理员:"+admin.getuName()+"更新了背景图"+filename);
            return Msg.success();
        }
        return Msg.fail();
    }
}
