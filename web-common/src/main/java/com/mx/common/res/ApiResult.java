package com.mx.common.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mx.common.globalexception.GlobalException;
import com.mx.common.globalexception.ResultEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果的操作类
 */
@Data
public class ApiResult<T>{
    private Integer code;

    private String msg;

    private T data;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String expandKeys;




    public ApiResult() {

    }

    public ApiResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResult(GlobalException anyException) {
        this.code = anyException.getCode();
        this.msg = anyException.getMessage();
    }

    public ApiResult(ResultEnum apiCodeEnum) {
        this.code = apiCodeEnum.getCode();
        this.msg = apiCodeEnum.getMessage();
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
    public String getExpandKeys() {
        return expandKeys;
    }

    public void setExpandKeys(String expandKeys) {
        this.expandKeys = expandKeys;
    }
    public ApiResult(Integer code, String msg, T data,String expandKeys) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.expandKeys = expandKeys;
    }}
