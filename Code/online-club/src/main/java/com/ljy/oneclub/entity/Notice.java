package com.ljy.oneclub.entity;

public class Notice {
    private Integer noticeId;

    private Integer noticeUserId;

    private Integer noticeToUserId;

    private String noticeType;

    private String noticeStatus;

    private Integer noticeSourceId;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getNoticeUserId() {
        return noticeUserId;
    }

    public void setNoticeUserId(Integer noticeUserId) {
        this.noticeUserId = noticeUserId;
    }

    public Integer getNoticeToUserId() {
        return noticeToUserId;
    }

    public void setNoticeToUserId(Integer noticeToUserId) {
        this.noticeToUserId = noticeToUserId;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType == null ? null : noticeType.trim();
    }

    public String getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus == null ? null : noticeStatus.trim();
    }

    public Integer getNoticeSourceId() {
        return noticeSourceId;
    }

    public void setNoticeSourceId(Integer noticeSourceId) {
        this.noticeSourceId = noticeSourceId;
    }
}