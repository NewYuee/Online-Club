package com.ljy.oneclub.vo;

import com.ljy.oneclub.entity.ClubMember;

import java.util.List;

public class ClubMemberTableData {
    private int code;
    private String msg;
    private List<ClubMember> data;
    private int count;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ClubMember> getData() {
        return data;
    }

    public void setData(List<ClubMember> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
