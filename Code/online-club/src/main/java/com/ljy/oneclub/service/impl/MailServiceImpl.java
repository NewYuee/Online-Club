package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.dao.UserMapper;
import com.ljy.oneclub.entity.Mail;
import com.ljy.oneclub.entity.User;
import com.ljy.oneclub.entity.UserExample;
import com.ljy.oneclub.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    private final static Logger logger= LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    JavaMailSenderImpl mailSender;

    @Override
    public void sendMail(Mail mail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        //发送带附件和内联元素的邮件需要将第二个参数设置为true
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true,"UTF-8");
        mimeMessageHelper.setFrom(mailSender.getUsername());
        mimeMessageHelper.setTo(mail.getToAdd());
        mimeMessageHelper.setSubject(mail.getSubject());
        mimeMessageHelper.setText(mail.getText(),true);
        if (mail.getFilePath()!=null){
            FileSystemResource file = new FileSystemResource(new File(mail.getFilePath()));
            String filename = file.getFilename();
            mimeMessageHelper.addAttachment(filename, file);
            logger.info("添加附件完成，fileName=>"+filename);
        }
        mailSender.send(message);
        logger.info("邮件发送完成(普通文本内容)");
    }

    @Override
    public void sendMailHtml(TemplateEngine templateEngine, Mail mail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
        helper.setFrom(mailSender.getUsername());
        helper.setTo(mail.getToAdd());
        helper.setSubject(mail.getSubject());
        if (mail.getFilePath()!=null){
            FileSystemResource file = new FileSystemResource(new File(mail.getFilePath()));
            String filename = file.getFilename();
            helper.addAttachment(filename, file);
            logger.info("添加附件完成，fileName=>"+filename);
        }
        //thymeleaf模版解析成String
        Context ctx = new Context();
        ctx.setVariable("name",mail.getToName());
        ctx.setVariable("validCode", mail.getValidateCode());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        ctx.setVariable("date", sdf.format(date));
        String htmlContent = templateEngine.process("mail/msg.html", ctx);
        helper.setText(htmlContent, true);
        mailSender.send(message);
        logger.info("邮件发送完成(html文本内容)");
    }

    @Autowired
    UserMapper userMapper;

    public void sendMailHtmlRemember(TemplateEngine templateEngine, String mail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
        helper.setFrom(mailSender.getUsername());
        helper.setTo(mail);
        helper.setSubject("密码找回");
        String userMail = mail;

        User user=null;
        if (userMail!=null){
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUMailAddEqualTo(mail);
            List<User> users = userMapper.selectByExample(userExample);
            if (users!=null){
                user=users.get(0);
            }
        }
        //thymeleaf模版解析成String
        Context ctx = new Context();
        ctx.setVariable("name",user.getuName());
        ctx.setVariable("mail",user.getuMailAdd());
        String htmlContent = templateEngine.process("mail/remember.html", ctx);
        helper.setText(htmlContent, true);
        mailSender.send(message);
        logger.info("找回密码邮件发送完成(html文本内容)");
    }
}
