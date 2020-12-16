package com.mx.shiro.entity;

import lombok.Data;

/**
 * 权限授权表
 */
@Data
public class AuthRolePermission {

    private Long id;

    //权限id
    private Long roleId;

    //权限规则id
    private Long permissionId;

    private String type;
}
