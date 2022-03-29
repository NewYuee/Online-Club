package com.ljy.oneclub.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Active {
    private Integer activeId;

    private Integer uId;

    private Integer activeType;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="UTC")
    private Date updateTime;

    private Integer viewedCount;

    private String activeTitle;

    private Integer liked;

    private String content;

    public Integer getActiveId() {
        return activeId;
    }

    public void setActiveId(Integer activeId) {
        this.activeId = activeId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getActiveType() {
        return activeType;
    }

    public void setActiveType(Integer activeType) {
        this.activeType = activeType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getViewedCount() {
        return viewedCount;
    }

    public void setViewedCount(Integer viewedCount) {
        this.viewedCount = viewedCount;
    }

    public String getActiveTitle() {
        return activeTitle;
    }

    public void setActiveTitle(String activeTitle) {
        this.activeTitle = activeTitle == null ? null : activeTitle.trim();
    }

    public Integer getLiked() {
        return liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}