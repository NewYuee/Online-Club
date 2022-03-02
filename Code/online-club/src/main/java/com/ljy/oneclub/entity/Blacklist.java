package com.ljy.oneclub.entity;

public class Blacklist {
    private Integer listId;

    private String userId;

    private String blacklistUserId;

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getBlacklistUserId() {
        return blacklistUserId;
    }

    public void setBlacklistUserId(String blacklistUserId) {
        this.blacklistUserId = blacklistUserId == null ? null : blacklistUserId.trim();
    }
}