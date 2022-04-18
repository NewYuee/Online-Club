package com.ljy.oneclub.vo;

import java.util.List;

public class ActiveTableData {
    private int code;
    private String msg;
    private List<ActiveJson> data;
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

    public List<ActiveJson> getData() {
        return data;
    }

    public void setData(List<ActiveJson> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
