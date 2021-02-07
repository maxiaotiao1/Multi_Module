package com.mx.probim.face.vo;

import lombok.Data;

@Data
public class FindPersonVo {
    private String id;// String 人员标识号
    private String idcardNum;// String 人员卡号
    private String name;// String 人员姓名
    //valid Object 有效期属性
    private Boolean enable; //Bool 是否启用有效期
    private String beginTime;// String 有效期开始时间
    private String endTime; //String 有效期结束时间
}
