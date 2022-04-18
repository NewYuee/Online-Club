package com.ljy.oneclub.entity;

import java.util.Date;

public class Pic {
    private Integer pId;

    private Integer pType;

    private String pName;

    private String pPath;

    private Integer belongToUserId;

    private Date updateTime;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getpType() {
        return pType;
    }

    public void setpType(Integer pType) {
        this.pType = pType;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public String getpPath() {
        return pPath;
    }

    public void setpPath(String pPath) {
        this.pPath = pPath == null ? null : pPath.trim();
    }

    public Integer getBelongToUserId() {
        return belongToUserId;
    }

    public void setBelongToUserId(Integer belongToUserId) {
        this.belongToUserId = belongToUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}