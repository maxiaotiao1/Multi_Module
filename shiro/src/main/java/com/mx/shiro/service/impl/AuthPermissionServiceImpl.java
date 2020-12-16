package com.mx.shiro.service.impl;

import com.mx.common.enums.ResultEnum;
import com.mx.common.exception.JsonException;
import com.mx.shiro.dao.AuthPermissionDao;
import com.mx.shiro.entity.AuthPermission;
import com.mx.shiro.service.AuthPermissionService;
import com.mx.shiro.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class AuthPermissionServiceImpl implements AuthPermissionService {

    @Resource
    private AuthPermissionDao authPermissionDao;

    /**
     * 根据多个id查询
     *
     * @param ids
     * @return
     */
    @Override
    public List<AuthPermission> listRuleByIdIn(List<Long> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        return authPermissionDao.listUrlAndRuleByIdIn(ids);
    }

    /**
     * 根据父级 pid 查询
     *
     * @param pid
     * @return
     */
    @Override
    public List<AuthPermission> listByPid(Long pid) {
        return authPermissionDao.listByPid(pid);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<AuthPermission> listAll() {
        return authPermissionDao.listAll();
    }

    /**
     * 插入
     *
     * @return
     */
    @Override
    public Result insertAuthPermission(AuthPermission authPermission) {

        // 查询是否存在，首先判断类型
        if (2 == authPermission.getType()){
            //是权限，则比较权限
            AuthPermission byRole = authPermissionDao.findByRule(authPermission.getRule());
            if (byRole != null) {
                return Result.error("当前role已存在");
            }
        }else {
            //是菜单则比较url
            AuthPermission byUrl = authPermissionDao.findByUrl(authPermission.getUrl());
            if (byUrl != null) {
                return Result.error("当前url已存在");
            }
        }
        authPermission.setCreateTime(new Date());
        authPermission.setUpdateTime(new Date());
        if (authPermission.getListorder() == null) {
            authPermission.setListorder(999);
        }
        System.out.println(authPermission);
        //添加一个
        if (authPermissionDao.insertAuthPermission(authPermission)){
            if(authPermission.getType() == 1){
                if (0 == authPermission.getPid()){
                    return Result.success();
                }
                //更新父节点
                AuthPermission  parentPermission = new AuthPermission();
                parentPermission.setId(authPermission.getPid());
                parentPermission.setType(0);
                if (authPermissionDao.updateAuthPermissionType(parentPermission)){
                    return Result.success();
                }else {
                    return Result.error("父节点更新失败");
                }
            }
            return Result.success();
        }else {
            return Result.success();
        }
    }

    /**
     * 更新
     *
     */
    @Override
    public boolean updateAuthPermission(AuthPermission authPermission) {

        if (authPermission.getUrl() != null) {
            // 查询是否存在
            AuthPermission byName = authPermissionDao.findByUrl(authPermission.getUrl());
            if (byName != null && !authPermission.getId().equals(byName.getId())) {
                throw new JsonException(ResultEnum.DATA_REPEAT, "当前权限规则已存在");
            }
        }

        authPermission.setUpdateTime(new Date());
        return authPermissionDao.updateAuthPermission(authPermission);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        AuthPermission authPermission = authPermissionDao.findById(id);
          if (authPermissionDao.deleteById(id)){
              //删除后的同级节点
              if (id !=null && id == 0){
                  //删除的是根节点
                  return true;
              }
              List<AuthPermission> childrenPermission = authPermissionDao.listByPid(authPermission.getPid());
              if (childrenPermission.isEmpty()){
                System.out.println("父节点的孩子节点为空");
                AuthPermission parentPermission = new AuthPermission();
                parentPermission.setId(authPermission.getPid());
                parentPermission.setType(1);
                if (!authPermissionDao.updateAuthPermissionType(parentPermission)){
                    System.out.println("更新父节点状态失败");
                }
                System.out.println("更新父节点状态成功");
                return true;
            }
            return true;
        }else {
            return false;
        }
    }
}
