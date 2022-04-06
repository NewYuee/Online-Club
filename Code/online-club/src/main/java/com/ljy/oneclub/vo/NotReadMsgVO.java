package com.ljy.oneclub.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class NotReadMsgVO {
    Integer messageCount;
    @JsonFormat(pattern="MM月dd日 HH:mm:ss",timezone="UTC")
    Date lastTime;
    String messageContent;
    int nowUid;
    String uid;
    String uname;
    String uProfilePicName;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getuProfilePicName() {
        return uProfilePicName;
    }

    public void setuProfilePicName(String uProfilePicName) {
        this.uProfilePicName = uProfilePicName;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getNowUid() {
        return nowUid;
    }

    public void setNowUid(int nowUid) {
        this.nowUid = nowUid;
    }


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
