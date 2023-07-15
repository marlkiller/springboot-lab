package com.example.springbootlab.common.vo;

import com.example.springbootlab.common.constant.ResultCode;

import java.io.Serializable;

public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 返回状态码
    private Integer code;

    // 返回体
    private T data;

    // 返回信息
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static <T> ResultVo<T> ok() {
        return returnResult(ResultCode.RC200.getCode(), ResultCode.RC200.getMessage(), null);
    }

    public static <T> ResultVo<T> ok(T data) {
        return returnResult(ResultCode.RC200.getCode(), null, data);
    }


    public static <T> ResultVo<T> fail() {
        return returnResult(ResultCode.RC500.getCode(), ResultCode.RC500.getMessage(), null);
    }

    public static <T> ResultVo<T> fail(String msg) {
        return returnResult(ResultCode.RC500.getCode(), msg, null);
    }

    public static <T> ResultVo<T> fail(ResultCode resultCode) {
        return returnResult(ResultCode.RC500.getCode(), resultCode.getMessage(), null);
    }


    // 统一返回信息
    public static <T> ResultVo<T> returnResult(Integer code, String msg, T data) {
        final ResultVo<T> result = new ResultVo<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
