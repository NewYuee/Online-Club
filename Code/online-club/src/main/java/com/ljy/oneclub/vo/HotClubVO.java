package com.ljy.oneclub.vo;

public class HotClubVO {
    private int clubId;
    private String name;
    private String headPicName;
    private int memberCount;
    private int recentActiveCount;
    private String intro;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getHeadPicName() {
        return headPicName;
    }

    public void setHeadPicName(String headPicName) {
        this.headPicName = headPicName;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public int getRecentActiveCount() {
        return recentActiveCount;
    }

    public void setRecentActiveCount(int recentActiveCount) {
        this.recentActiveCount = recentActiveCount;
    }
}
