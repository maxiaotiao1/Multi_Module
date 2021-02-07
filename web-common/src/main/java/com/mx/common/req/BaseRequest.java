package com.mx.common.req;

import lombok.Data;
import java.util.UUID;

@Data
public class BaseRequest {

    /**
     * 唯一请求号
     */
    private String reqNo;

    /**
     * 请求的时间戳
     */
    private Long timeStamp;

    public BaseRequest() {
        this.reqNo = UUID.randomUUID().toString();
        this.timeStamp = System.currentTimeMillis();
    }
}
