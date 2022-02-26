package com.ljy.oneclub.msg;

import java.util.HashMap;

/**
 * 返回前端的一个数据实体
 */
public class Msg {
    private int code;
    private String msg;
    private HashMap<String,Object> data=new HashMap<>();


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() { return msg; }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HashMap<String, Object> getData() { return data; }

    public void setData(HashMap<String, Object> data) { this.data = data; }

    public static Msg success(){
        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMsg("处理成功!");
        return msg;
    }

    public static Msg fail(){
        Msg msg = new Msg();
        msg.setCode(400);
        msg.setMsg("处理失败!");
        return msg;
    }

    public Msg addData(String key, Object object){
        this.getData().put(key, object);
        return this;
    }

}
