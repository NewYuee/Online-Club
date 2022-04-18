package com.ljy.oneclub.vo;

public class NoticeOfLike {
    private Integer noticeId;

    private Integer noticeUserId;

    private Integer noticeToUserId;

    private String noticeType;

    private String noticeStatus;

    private Integer noticeSourceId;

    private Integer isArticle;

    private String noticeUserName;

    private String decrips;


    public Integer getIsArticle() {
        return isArticle;
    }

    public void setIsArticle(Integer isArticle) {
        this.isArticle = isArticle;
    }

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
        this.noticeType = noticeType;
    }

    public String getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public Integer getNoticeSourceId() {
        return noticeSourceId;
    }

    public void setNoticeSourceId(Integer noticeSourceId) {
        this.noticeSourceId = noticeSourceId;
    }

    public String getNoticeUserName() {
        return noticeUserName;
    }

    public void setNoticeUserName(String noticeUserName) {
        this.noticeUserName = noticeUserName;
    }

    public String getDecrips() {
        return decrips;
    }

    public void setDecrips(String decrips) {
        this.decrips = decrips;
    }
}
