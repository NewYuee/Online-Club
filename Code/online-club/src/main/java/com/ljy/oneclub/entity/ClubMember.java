package com.ljy.oneclub.entity;

import java.util.Date;

public class ClubMember {
    private Integer id;

    private Integer memUserId;

    private String memName;

    private String gender;

    private String memTelNum;

    private String memDetailInfo;

    private Date memJoinTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemUserId() {
        return memUserId;
    }

    public void setMemUserId(Integer memUserId) {
        this.memUserId = memUserId;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName == null ? null : memName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getMemTelNum() {
        return memTelNum;
    }

    public void setMemTelNum(String memTelNum) {
        this.memTelNum = memTelNum == null ? null : memTelNum.trim();
    }

    public String getMemDetailInfo() {
        return memDetailInfo;
    }

    public void setMemDetailInfo(String memDetailInfo) {
        this.memDetailInfo = memDetailInfo == null ? null : memDetailInfo.trim();
    }

    public Date getMemJoinTime() {
        return memJoinTime;
    }

    public void setMemJoinTime(Date memJoinTime) {
        this.memJoinTime = memJoinTime;
    }
}