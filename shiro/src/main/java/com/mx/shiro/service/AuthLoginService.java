package com.mx.shiro.service;

import java.util.List;

public interface AuthLoginService {

    //获取权限列表
    List<String> listRuleByAdminId(Long adminId);
    //获取菜单列表
    List<String> listUrlByAdminId(Long adminId);

}
