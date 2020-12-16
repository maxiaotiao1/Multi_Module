package com.mx.common.exception;

import com.mx.common.enums.ResultEnum;
import lombok.Getter;

/**
 * 错误处理类
 */
@Getter
public class JsonException extends RuntimeException {

    private Integer code;

    public JsonException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public JsonException(ResultEnum resultEnum, String message) {
        super(message);
        this.code = resultEnum.getCode();
    }

    public JsonException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
