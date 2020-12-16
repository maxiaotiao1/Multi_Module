package com.mx.common.res;

import lombok.Data;

/**
 * 返回结果类
 *
 * @param <T>
 */
@Data
public class BaseResponse<T> {

    //状态码
    private Integer code;

    //返回信息
    private String message;

    //返回数据
    private T data;
}
