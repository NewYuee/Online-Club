package com.ljy.oneclub.service;

import com.ljy.oneclub.entity.Mail;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;

public interface MailService {
    void sendMail(Mail mail) throws MessagingException;
    void sendMailHtml(TemplateEngine templateEngine,Mail mail) throws MessagingException;
}
