package com.wounom.kaoyanircpadmin.entity;

/**
 * @author litind
 * @version 1.0
 * @date 2023/3/30 16:36
 */

public class Result {

    private Integer code = 0;
    private String message;
    private long count = 0L;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String message) {
        this.message = message;
    }

    public long getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(Integer code, String message, long count, Object data) {
        this.code = code;
        this.message = message;
        this.count = count;
        this.data = data;
    }

    public Result(String message) {
        this.message = message;
    }

    public Result(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.message = message;
        this.code = code;
    }


    @Override
    public String toString() {
        return "ReturnJson{" +
                "code=" + code +
                ", msg='" + message + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
