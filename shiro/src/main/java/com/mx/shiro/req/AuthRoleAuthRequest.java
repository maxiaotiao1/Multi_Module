package com.mx.shiro.req;

import lombok.Data;

import java.util.List;

/**
 * 角色的授权提交表单
 */
@Data
public class AuthRoleAuthRequest {
    //@NotNull(message = "请选择角色")
    private Long roleId;
    //@NotEmpty(message = "请选择授权的权限规则")
    private List<Long> authRules;
}
