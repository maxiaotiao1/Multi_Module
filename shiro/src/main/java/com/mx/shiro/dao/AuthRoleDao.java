package com.mx.shiro.dao;

import com.mx.shiro.entity.AuthRole;
import com.mx.shiro.req.AuthRoleQueryRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AuthRoleDao {

    /**
     * 后台管理业务查询列表
     */
    List<AuthRole> listAdminPage(AuthRoleQueryRequest authRoleQueryRequest);

    /**
     * 返回id,name 字段的列表
     *
     * @return 列表
     */
    List<AuthRole> listAuthAdminRolePage(Integer status);

    AuthRole findByName(String name);

    /**
     * 插入
     *
     * @param authAdmin
     * @return
     */
    boolean insertAuthRole(AuthRole authAdmin);

    /**
     * 更新
     *
     * @param authAdmin
     * @return
     */
    boolean updateAuthRole(AuthRole authAdmin);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean deleteById(Long id);

}
