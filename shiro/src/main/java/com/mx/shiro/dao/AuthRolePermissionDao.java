package com.mx.shiro.dao;

import com.mx.shiro.entity.AuthRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AuthRolePermissionDao {

    /**
     * 根据roleIds查询
     *
     * @param roleIds 传入的id
     * @return
     */
    List<AuthRolePermission> listByRoleIdIn(List<Long> roleIds);

    /**
     * 根据 roleId 查询
     *
     * @param roleId 传入的id
     * @return
     */
    @Select("select permission_id from auth_role_permission where role_id = #{roleId}")
    List<AuthRolePermission> listByRoleId(Long roleId);


    /**
     * 批量插入
     *
     * @param authPermissionList
     * @return
     */
    int insertAuthPermissionAll(List<AuthRolePermission> authPermissionList);

    /**
     * 根据角色id删除
     *
     * @param roleId
     * @return
     */
    boolean deleteByRoleId(Long roleId);

}
