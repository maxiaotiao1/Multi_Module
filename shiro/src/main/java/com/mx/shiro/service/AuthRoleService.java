package com.mx.shiro.service;

import com.mx.shiro.entity.AuthRole;
import com.mx.shiro.req.AuthRoleQueryRequest;

import java.util.List;

public interface AuthRoleService {

    List<AuthRole> listAdminPage(AuthRoleQueryRequest authRoleQueryRequest);

    List<AuthRole> listAuthAdminRolePage(Integer page, Integer limit, Integer status);

    AuthRole findByName(String name);

    boolean insertAuthRole(AuthRole authRole);

    boolean updateAuthRole(AuthRole authRole);

    boolean deleteById(Long id);

}
