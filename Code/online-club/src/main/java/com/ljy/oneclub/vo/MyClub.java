package com.ljy.oneclub.vo;

public class MyClub {
    private Integer clubId;
    private String clubName;
    private String headPic;
    private String intro;
    private int hotVal;

    public int getHotVal() {
        return hotVal;
    }

    public void setHotVal(int hotVal) {
        this.hotVal = hotVal;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
