package com.mx.shiro.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * 权限规则的提交保存表单
 */
@Data
public class AuthPermissionSaveRequest {
    private Long id;
    private Long pid;
    @NotEmpty(message = "请输入规则名称")
    private String name;
    @NotEmpty(message = "请输入规则标题")
    private String title;
    @NotNull(message = "请选择状态")
    private Integer status;
    private Integer type;
    private String url;
    private Integer listorder;

}
