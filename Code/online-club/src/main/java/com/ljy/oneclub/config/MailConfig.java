package com.ljy.oneclub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Collections;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig {


    private String host;

    private String username;

    private String pwd;

    private String encode;

    public String getHost() {
        return host;
    }

    @Value("${mail.host}")
    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    @Value("${mail.username}")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    @Value("${mail.password}")
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEncode() {
        return encode;
    }

    @Value("${mail.encoding}")
    public void setEncode(String encode) {
        this.encode = encode;
    }

    @Bean
    public JavaMailSenderImpl javaMailSenderImpl(){
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost(host);
        javaMailSenderImpl.setUsername(username);
        javaMailSenderImpl.setPassword(pwd);
        javaMailSenderImpl.setDefaultEncoding(encode);
        return javaMailSenderImpl;
    }


}
