package com.ljy.oneclub.vo;

import java.util.List;

public class ClubAppTable {
    private int code;
    private String msg;
    private List<ApplicationJson> data;
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

    public List<ApplicationJson> getData() {
        return data;
    }

    public void setData(List<ApplicationJson> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
