package com.ljy.oneclub.vo;

public class CommentVO {
    private int c_id;
    private int u_id;
    private String u_name;
    private String reply_u_name;
    private int reply_u_id;
    private String content;

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

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getReply_u_name() {
        return reply_u_name;
    }

    public void setReply_u_name(String reply_u_name) {
        this.reply_u_name = reply_u_name;
    }

    public int getReply_u_id() {
        return reply_u_id;
    }

    public void setReply_u_id(int reply_u_id) {
        this.reply_u_id = reply_u_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
