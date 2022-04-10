package com.ljy.oneclub.vo;

import com.ljy.oneclub.entity.User;

import java.util.List;

public class ClubTableData {
    private int code;
    private String msg;
    private List<ClubTableJson> data;
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

    public List<ClubTableJson> getData() {
        return data;
    }

    public void setData(List<ClubTableJson> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
