package com.mx.shiro.service;


import com.mx.shiro.entity.AuthPermission;
import com.mx.shiro.utils.Result;

import java.util.List;

public interface AuthPermissionService {


    List<AuthPermission> listRuleByIdIn(List<Long> ids);


    List<AuthPermission> listByPid(Long pid);

    List<AuthPermission> listAll();

    Result insertAuthPermission(AuthPermission authPermissionRule);

    boolean updateAuthPermission(AuthPermission authPermissionRule);

    boolean deleteById(Long id);


}
