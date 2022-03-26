package com.ljy.oneclub.entity;

import java.util.Date;

public class ClubMember {
    private Integer id;

    private Integer clubId;

    private Integer userId;

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

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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