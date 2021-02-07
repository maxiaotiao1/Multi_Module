package com.mx.shiro.service;

import com.mx.shiro.entity.AuthRolePermission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AuthRolePermissionService {

    List<AuthRolePermission> listByRoleIdIn(List<Long> roleIds);

    List<AuthRolePermission> listByRoleId(Long roleId);

    int insertAuthPermissionAll(List<AuthRolePermission> authPermissionList);

    boolean deleteByRoleId(Long roleId);

}
