package com.mx.shiro.req;

import com.mx.common.req.ListPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthAdminQueryRequest extends ListPageRequest {

    private String username;

    private Integer status;

    private Long roleId;

    private List<Long> ids;

}
