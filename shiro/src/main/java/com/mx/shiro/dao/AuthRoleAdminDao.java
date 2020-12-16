package com.mx.shiro.dao;

import com.mx.shiro.entity.AuthRoleAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AuthRoleAdminDao {

    /**
     * 根据 adminId 查询角色
     */
    @Select("SELECT role_id FROM auth_role_admin where admin_id = #{adminId}")
    List<AuthRoleAdmin> listByAdminId(Long adminId);

    /**
     * 根据 多个 adminId 查询
     */
    List<AuthRoleAdmin> listByAdminIdIn(List<Long> adminIds);

    /**
     * 根据 role_id 查询 admin_id
     */
    List<AuthRoleAdmin> listByRoleId(Long roleId);

    /**
     * 批量插入
     */
    int insertAuthRoleAdminAll(@Param("list") List<AuthRoleAdmin> authRoleAdminList);


    /**
     * 根据 adminId 删除
     */
    boolean deleteByAdminId(Long adminId);
}
