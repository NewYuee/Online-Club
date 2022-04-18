package com.ljy.oneclub.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Application {
    private Integer appId;

    private Integer appType;

    private Integer appUserId;

    private Integer appToUserId;

    private String appUserName;

    private String appGender;

    private String appUserTelNum;

    private String appUserDetailInfo;

    private String appReason;

    private String appFile;

    private Integer appStatus;

    private String appDealResult;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date appTime;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public Integer getAppToUserId() {
        return appToUserId;
    }

    public void setAppToUserId(Integer appToUserId) {
        this.appToUserId = appToUserId;
    }

    public String getAppUserName() {
        return appUserName;
    }

    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName == null ? null : appUserName.trim();
    }

    public String getAppGender() {
        return appGender;
    }

    public void setAppGender(String appGender) {
        this.appGender = appGender == null ? null : appGender.trim();
    }

    public String getAppUserTelNum() {
        return appUserTelNum;
    }

    public void setAppUserTelNum(String appUserTelNum) {
        this.appUserTelNum = appUserTelNum == null ? null : appUserTelNum.trim();
    }

    public String getAppUserDetailInfo() {
        return appUserDetailInfo;
    }

    public void setAppUserDetailInfo(String appUserDetailInfo) {
        this.appUserDetailInfo = appUserDetailInfo == null ? null : appUserDetailInfo.trim();
    }

    public String getAppReason() {
        return appReason;
    }

    public void setAppReason(String appReason) {
        this.appReason = appReason == null ? null : appReason.trim();
    }

    public String getAppFile() {
        return appFile;
    }

    public void setAppFile(String appFile) {
        this.appFile = appFile == null ? null : appFile.trim();
    }

    public Integer getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(Integer appStatus) {
        this.appStatus = appStatus;
    }

    public String getAppDealResult() {
        return appDealResult;
    }

    public void setAppDealResult(String appDealResult) {
        this.appDealResult = appDealResult == null ? null : appDealResult.trim();
    }

    public Date getAppTime() {
        return appTime;
    }

    public void setAppTime(Date appTime) {
        this.appTime = appTime;
    }
}