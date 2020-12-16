package com.mx.shiro.service.impl;

import com.mx.common.constant.CacheConstant;
import com.mx.common.util.CacheUtils;
import com.mx.shiro.entity.AuthRolePermission;
import com.mx.shiro.entity.AuthPermission;
import com.mx.shiro.entity.AuthRoleAdmin;
import com.mx.shiro.service.AuthLoginService;
import com.mx.shiro.service.AuthPermissionService;
import com.mx.shiro.service.AuthRolePermissionService;
import com.mx.shiro.service.AuthRoleAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthLoginServiceImpl implements AuthLoginService {

    @Resource
    private AuthRoleAdminService authRoleAdminService;

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Resource
    private AuthPermissionService authPermissionService;


    /**
     * 根据 管理员id 获取权限
     *
     * @param adminId
     * @return
     */
    @Override
    public List<String> listRuleByAdminId(Long adminId) {

        List<String> authRules = new ArrayList<>();
        // 超级管理员
        if (adminId.equals(1L)) {
            authRules.add("admin");
            return authRules;
        }

        // 如果存在，先从缓存中获取权限
        String aarKey = String.format(CacheConstant.ADMIN_AUTH_RULES, adminId);
        if (CacheUtils.hasKey(aarKey)) {
            return new ArrayList<>(CacheUtils.sGetMembers(aarKey));
        }
        log.info("开始获取数据库中的用户的权限规则列表");

        // 获取角色ids
        List<AuthRoleAdmin> authRoleAdmins = authRoleAdminService.listByAdminId(adminId);
        List<Long> roleIds = authRoleAdmins.stream().map(AuthRoleAdmin::getRoleId).collect(Collectors.toList());

        // 角色授权列表
        List<AuthRolePermission> authPermissions = authRolePermissionService.listByRoleIdIn(roleIds);
        List<Long> permissionRuleIds = authPermissions.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());

        // 获取授权的规则
        List<AuthPermission> authPermissionRules = authPermissionService.listRuleByIdIn(permissionRuleIds);

        // 获取权限列表
        authRules = authPermissionRules.stream().map(AuthPermission::getRule).collect(Collectors.toList());

        // 如果为空，则添加一个空值
        if (authRules.isEmpty()) {
            authRules.add("");
        }

        String[] strings = authRules.toArray(new String[0]);
        CacheUtils.sAdd(aarKey, strings);
        CacheUtils.expire(aarKey, 7200L); // 两小时后过期

        return authRules;
    }

    @Override
    public List<String> listUrlByAdminId(Long adminId) {
        List<String> menus = new ArrayList<>();
        // 超级管理员
        if (adminId.equals(1L)) {
            menus.add("admin");
            return menus;
        }

        // 如果存在，先从缓存中获取权限
        String aarKey = String.format(CacheConstant.ADMIN_AUTH_MENUS, adminId);
        if (CacheUtils.hasKey(aarKey)) {
            return new ArrayList<>(CacheUtils.sGetMembers(aarKey));
        }
        log.info("开始获取数据库中的用户的菜单");

        // 获取角色ids
        List<AuthRoleAdmin> authRoleAdmins = authRoleAdminService.listByAdminId(adminId);
        List<Long> roleIds = authRoleAdmins.stream().map(AuthRoleAdmin::getRoleId).collect(Collectors.toList());

        // 角色授权列表
        List<AuthRolePermission> authPermissions = authRolePermissionService.listByRoleIdIn(roleIds);
        List<Long> permissionRuleIds = authPermissions.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());

        // 获取授权的规则
        List<AuthPermission> authPermissionRules = authPermissionService.listRuleByIdIn(permissionRuleIds);

        // 获取菜单列表
        menus = authPermissionRules.stream().map(AuthPermission::getUrl).collect(Collectors.toList());

        // 如果为空，则添加一个空值
        if (menus.isEmpty()) {
            menus.add("");
        }

        String[] strings = menus.toArray(new String[0]);
        CacheUtils.sAdd(aarKey, strings);
        CacheUtils.expire(aarKey, 7200L); // 两小时后过期

        return menus;
    }

}
