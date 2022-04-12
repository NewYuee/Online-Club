package com.ljy.oneclub.vo;

import java.util.List;

public class CommentTableData {
    private int code;
    private String msg;
    private List<CommentJson> data;
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

    public List<CommentJson> getData() {
        return data;
    }

    public void setData(List<CommentJson> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
