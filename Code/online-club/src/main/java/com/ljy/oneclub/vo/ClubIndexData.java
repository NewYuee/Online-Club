package com.ljy.oneclub.vo;

public class ClubIndexData {
    private int applyCount;
    private int memberCount;
    private int activeCount;
    private int todayActive;

    public ClubIndexData(int applyCount, int memberCount, int activeCount, int todayActive) {
        this.applyCount = applyCount;
        this.memberCount = memberCount;
        this.activeCount = activeCount;
        this.todayActive = todayActive;
    }

    public int getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(int applyCount) {
        this.applyCount = applyCount;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(int activeCount) {
        this.activeCount = activeCount;
    }

    public int getTodayActive() {
        return todayActive;
    }

    public void setTodayActive(int todayActive) {
        this.todayActive = todayActive;
    }
}
