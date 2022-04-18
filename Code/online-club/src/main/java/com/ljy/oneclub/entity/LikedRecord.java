package com.ljy.oneclub.entity;

import java.util.Date;

public class LikedRecord {
    private Integer likeId;

    private Integer uId;

    private Integer likeType;

    private Integer likeActiveId;

    private Date likeTime;

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getLikeType() {
        return likeType;
    }

    public void setLikeType(Integer likeType) {
        this.likeType = likeType;
    }

    public Integer getLikeActiveId() {
        return likeActiveId;
    }

    public void setLikeActiveId(Integer likeActiveId) {
        this.likeActiveId = likeActiveId;
    }

    public Date getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }
}