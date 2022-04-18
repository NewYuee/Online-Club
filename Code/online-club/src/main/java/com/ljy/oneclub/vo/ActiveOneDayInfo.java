package com.ljy.oneclub.vo;

public class ActiveOneDayInfo {
    private int activeId;
    private String activeTitle;
    private int viewCount;
    private int likeCount;
    private double hotValue;
    private String time;

    public int getActiveId() {
        return activeId;
    }

    public void setActiveId(int activeId) {
        this.activeId = activeId;
    }

    public String getActiveTitle() {
        return activeTitle;
    }

    public void setActiveTitle(String activeTitle) {
        this.activeTitle = activeTitle;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public double getHotValue() {
        return hotValue;
    }

    public void setHotValue(double hotValue) {
        this.hotValue = hotValue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
