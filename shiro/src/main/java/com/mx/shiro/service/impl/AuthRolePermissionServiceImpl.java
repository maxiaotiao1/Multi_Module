package com.mx.shiro.service.impl;

import com.mx.shiro.dao.AuthRolePermissionDao;
import com.mx.shiro.entity.AuthRolePermission;
import com.mx.shiro.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class AuthRolePermissionServiceImpl implements AuthRolePermissionService {

    @Resource
    private AuthRolePermissionDao authRolePermissionDao;

    /**
     * 根据 多个角色id 查询
     *
     * @param roleIds
     * @return
     */
    @Override
    public List<AuthRolePermission> listByRoleIdIn(List<Long> roleIds) {
        if (roleIds.isEmpty()) {
            return Collections.emptyList();
        }
        return authRolePermissionDao.listByRoleIdIn(roleIds);
    }

    /**
     * 根据某个角色id 查询
     *
     * @param roleId
     * @return
     */
    @Override
    public List<AuthRolePermission> listByRoleId(Long roleId) {
        return authRolePermissionDao.listByRoleId(roleId);
    }

    /**
     * 批量插入
     *
     * @param authPermissionList
     * @return
     */
    @Override
    public int insertAuthPermissionAll(List<AuthRolePermission> authPermissionList) {
        return authRolePermissionDao.insertAuthPermissionAll(authPermissionList);
    }

    /**
     * 根据角色id删除
     *
     * @param roleId
     * @return
     */
    @Override
    public boolean deleteByRoleId(Long roleId) {
        return authRolePermissionDao.deleteByRoleId(roleId);
    }
}
