package com.mx.shiro.req;

import com.mx.common.req.ListPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色的查询表单
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthRoleQueryRequest extends ListPageRequest {
    private String name;
    private Integer status;

}
