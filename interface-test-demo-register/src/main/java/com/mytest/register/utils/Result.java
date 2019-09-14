package com.mytest.register.utils;

/**
 * @author gdufeZLYL
 * @date 2019/9/14 11:46
 */
public class Result<T> {
    /**
     * status
     * 状态码
     */
    private Integer code;

    /**
     * message
     * 消息
     */
    private String msg;

    /**
     * data
     */
    private T data;

    public Result(){}

    public Result(Integer code , String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Status{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
