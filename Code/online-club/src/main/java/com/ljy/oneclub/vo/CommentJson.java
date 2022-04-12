package com.ljy.oneclub.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CommentJson {

    private int c_id;
    private int u_id;
    private String u_name;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="UTC")
    private Date updateTime;

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
