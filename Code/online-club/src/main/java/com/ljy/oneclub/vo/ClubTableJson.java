package com.ljy.oneclub.vo;

public class ClubTableJson {
    private int clubId;
    private String clubName;
    private int activeCount;
    private int memberships;

    public int getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(int activeCount) {
        this.activeCount = activeCount;
    }

    public int getMemberships() {
        return memberships;
    }

    public void setMemberships(int memberships) {
        this.memberships = memberships;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

}
