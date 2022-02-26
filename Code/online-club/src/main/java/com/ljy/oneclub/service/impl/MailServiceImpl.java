package com.ljy.oneclub.service.impl;

import com.ljy.oneclub.entity.Mail;
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

@Service
public class MailServiceImpl implements MailService {

    private final static Logger logger= LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Override
    public void sendMail(Mail mail) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        //发送带附件和内联元素的邮件需要将第二个参数设置为true
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
        mimeMessageHelper.setFrom(javaMailSender.getUsername());
        mimeMessageHelper.setTo(mail.getToAdd());
        mimeMessageHelper.setSubject(mail.getSubject());
        mimeMessageHelper.setText(mail.getText(),true);
        if (mail.getFilePath()!=null){
            FileSystemResource file = new FileSystemResource(new File(mail.getFilePath()));
            String filename = file.getFilename();
            mimeMessageHelper.addAttachment(filename, file);
            logger.info("添加附件完成，fileName=>"+filename);
        }
        javaMailSender.send(message);
        logger.info("邮件发送完成(普通文本内容)");
    }

    @Override
    public void sendMailHtml(TemplateEngine templateEngine, Mail mail) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(javaMailSender.getUsername());
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        ctx.setVariable("date", sdf.format(date));
        String htmlContent = templateEngine.process("mail/msg.html", ctx);
        helper.setText(htmlContent, true);
        javaMailSender.send(message);
        logger.info("邮件发送完成(html文本内容)");
    }
}
