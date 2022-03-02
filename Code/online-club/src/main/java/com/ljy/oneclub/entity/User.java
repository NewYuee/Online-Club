package com.ljy.oneclub.entity;

public class User {
    private Integer uId;

    private String uName;

    private String uPassword;

    private String uMailAdd;

    private Integer uAuthNo;

    private String uProfile;

    private String uProfilePhotoName;

    private String uProfileBackgroundimgName;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword == null ? null : uPassword.trim();
    }

    public String getuMailAdd() {
        return uMailAdd;
    }

    public void setuMailAdd(String uMailAdd) {
        this.uMailAdd = uMailAdd == null ? null : uMailAdd.trim();
    }

    public Integer getuAuthNo() {
        return uAuthNo;
    }

    public void setuAuthNo(Integer uAuthNo) {
        this.uAuthNo = uAuthNo;
    }

    public String getuProfile() {
        return uProfile;
    }

    public void setuProfile(String uProfile) {
        this.uProfile = uProfile == null ? null : uProfile.trim();
    }

    public String getuProfilePhotoName() {
        return uProfilePhotoName;
    }

    public void setuProfilePhotoName(String uProfilePhotoName) {
        this.uProfilePhotoName = uProfilePhotoName == null ? null : uProfilePhotoName.trim();
    }

    public String getuProfileBackgroundimgName() {
        return uProfileBackgroundimgName;
    }

    public void setuProfileBackgroundimgName(String uProfileBackgroundimgName) {
        this.uProfileBackgroundimgName = uProfileBackgroundimgName == null ? null : uProfileBackgroundimgName.trim();
    }
}