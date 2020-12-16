package com.mx.shiro.res;

import lombok.Data;

/**
 * 角色视图
 */
@Data
public class AuthRoleResponse {

    private Long id;
    private String name;
    private Long pid;
    private Integer status;
    private String remark;
    private Integer listorder;

}
