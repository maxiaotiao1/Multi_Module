package com.mx.shiro.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    //读取错误
    final public static int ERR = -1;
    //写入成功
    final public static int SUCCESS = 0;

    private int code =-1;
    private String message;
    private HashMap<String,Object> map;

    public Result(){
    }

    public Result(int code, String message,HashMap map){
        this.code = code;
        this.message = message;
        this.map = map;
    }

    public Result(int code, String message){
        this.code = code;
        this.message = message;
    }

    public static Result success(){
        return new Result(SUCCESS,"成功");
    }

    public static Result data(HashMap map){
        return new Result(SUCCESS,"成功",map);
    }

    public static Result success(String  message){
        return new Result(SUCCESS,message);
    }

    public static Result error(){
        return new Result(SUCCESS,"失败");
    }

    public static Result error(String  message){
        return new Result(ERR,message);
    }
}
