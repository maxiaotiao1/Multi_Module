package com.mx.shiro.req;

import lombok.Data;

/**
 * 角色的提交保存表单
 */
@Data
public class AuthRoleSaveRequest {
    private Long id;
    private String name;
    private Long pid;
    private Integer status;
    private String remark;
    private Long listorder;
}
