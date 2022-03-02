package com.ljy.oneclub.entity;

public class ClubContact {
    private Integer id;

    private Integer clubId;

    private String realName;

    private String telNum;

    private String email;

    private String departmentInClub;

    private String positionInClub;

    private String other;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum == null ? null : telNum.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDepartmentInClub() {
        return departmentInClub;
    }

    public void setDepartmentInClub(String departmentInClub) {
        this.departmentInClub = departmentInClub == null ? null : departmentInClub.trim();
    }

    public String getPositionInClub() {
        return positionInClub;
    }

    public void setPositionInClub(String positionInClub) {
        this.positionInClub = positionInClub == null ? null : positionInClub.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }
}