package com.mx.common.globalexception;
import lombok.Data;

@Data
public class GlobalException extends RuntimeException {
    private Integer code;

    public GlobalException(String message) {
        super(message);
        this.code = ResultEnum.FAILURE.getCode();
    }

    public GlobalException(ResultEnum resultEnum, String message) {
        super(message);
        this.code = resultEnum.getCode();
    }


}
