package com.mx.shiro.entity;

import lombok.Data;

import java.util.Date;

/**
 * 规则表
 */
@Data
public class AuthPermission {

    private Long id;
    //父级id
    private Long pid;
    //菜单url
    private String url;
    //规则中文名
    private String title;
    //权限状态
    private Integer status;
    //权限类型（1、菜单，2、按钮）
    private Integer type;
    //规则表达式，为空表示存在就验证，不为空表示按照条件验证
    private String rule;
    //排序，优先级，越小优先级越高
    private Integer listorder;
    private Date createTime;
    private Date updateTime;

}
