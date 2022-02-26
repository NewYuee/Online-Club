package com.ljy.oneclub.entity;

public class Mail {
    private String toName;
    private String toAdd;
    private String subject;
    private String text;//可以是一个thymeleaf的html文件位置
    private String filePath;
    private String validateCode;
    private long outdate;

    public Mail(String toName, String toAdd, String subject, String text, String filePath) {
        this.toName = toName;
        this.toAdd = toAdd;
        this.subject = subject;
        this.text = text;
        this.filePath = filePath;
    }

    public Mail(String toName, String toAdd, String subject, String text, String filePath, String validateCode, long outdate) {
        this.toName = toName;
        this.toAdd = toAdd;
        this.subject = subject;
        this.text = text;
        this.filePath = filePath;
        this.validateCode = validateCode;
        this.outdate = outdate;
    }

    public long getOutdate() {
        return outdate;
    }

    public void setOutdate(long outdate) {
        this.outdate = outdate;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToAdd() {
        return toAdd;
    }

    public void setToAdd(String toAdd) {
        this.toAdd = toAdd;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
