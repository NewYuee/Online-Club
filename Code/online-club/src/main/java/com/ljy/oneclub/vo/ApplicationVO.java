package com.ljy.oneclub.vo;

public class ApplicationVO {
    int applyId;
    int applyToUid;
    String applyToName;
    String type;
    String status;

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public int getApplyToUid() {
        return applyToUid;
    }

    public void setApplyToUid(int applyToUid) {
        this.applyToUid = applyToUid;
    }

    public String getApplyToName() {
        return applyToName;
    }

    public void setApplyToName(String applyToName) {
        this.applyToName = applyToName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
