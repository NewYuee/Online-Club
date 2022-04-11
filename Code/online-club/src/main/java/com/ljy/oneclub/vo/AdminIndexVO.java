package com.ljy.oneclub.vo;

public class AdminIndexVO {
    private int userCount;
    private int clubCount;
    private int activeCount;
    private int activeDayCount;

    public AdminIndexVO(int userCount, int clubCount, int activeCount, int activeDayCount) {
        this.userCount = userCount;
        this.clubCount = clubCount;
        this.activeCount = activeCount;
        this.activeDayCount = activeDayCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getClubCount() {
        return clubCount;
    }

    public void setClubCount(int clubCount) {
        this.clubCount = clubCount;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(int activeCount) {
        this.activeCount = activeCount;
    }

    public int getActiveDayCount() {
        return activeDayCount;
    }

    public void setActiveDayCount(int activeDayCount) {
        this.activeDayCount = activeDayCount;
    }
}
