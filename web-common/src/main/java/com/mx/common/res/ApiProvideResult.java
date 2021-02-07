package com.mx.common.res;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.mx.common.globalexception.GlobalException;
import com.mx.common.globalexception.ResultEnum;


public class ApiProvideResult<T> {

    private Integer returnCode;

    private String errorString;

    private T data;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String expandKeys;




    public ApiProvideResult() {

    }

    public ApiProvideResult(Integer returnCode, String errorString, T data) {
        this.returnCode = returnCode;
        this.errorString = errorString;
        this.data = data;
    }

    public ApiProvideResult(Integer returnCode, String errorString) {
        this.returnCode = returnCode;
        this.errorString = errorString;
    }

    public ApiProvideResult(GlobalException GlobalException) {
        this.returnCode = GlobalException.getCode();
        this.errorString = GlobalException.getMessage();
    }

    public ApiProvideResult(ResultEnum ResultEnum) {
        this.returnCode = ResultEnum.getCode();
        this.errorString = ResultEnum.getMessage();
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String msg) {
        this.errorString = errorString;
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
    public ApiProvideResult(Integer returnCode, String errorString, T data, String expandKeys) {
        this.returnCode = returnCode;
        this.errorString = errorString;
        this.data = data;
        this.expandKeys = expandKeys;
    }
}
