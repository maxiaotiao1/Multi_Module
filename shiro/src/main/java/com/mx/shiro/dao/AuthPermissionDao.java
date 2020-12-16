package com.mx.shiro.dao;

import com.mx.shiro.entity.AuthPermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface AuthPermissionDao {
    //删除权限
    @Delete("delete from auth_permission where id = #{id}")
    boolean deleteById(Long id);

    //根据id 搜索权限
    @Select("select pid from auth_permission where id = #{id}")
     AuthPermission findById(Long id);

    /**
     * 查询所有
     */
    @Select("SELECT `id`,`pid`,`url`,`title`,`status`,`rule`,`type`,`listorder` FROM auth_permission")
    List<AuthPermission> listAll();

    /**
     * 根据 父级 pid 查询
     */
    @Select("SELECT id,pid,url,title,status,rule,type,listorder FROM auth_permission where pid = #{pid}")
    List<AuthPermission> listByPid(Long pid);

    /**
     * 根据 url 地址
     */
    @Select("SELECT `id` FROM auth_permission where `url`= #{url}  limit 1")
    AuthPermission findByUrl(String url);

    /**
     * 根据 rule 地址
     */
    @Select("SELECT `id` FROM auth_permission where `rule`= #{rule}  limit 1")
    AuthPermission findByRule(String role);

    /**
     * 插入
     */
    boolean insertAuthPermission(AuthPermission authPermissionRule);

    /**
     * 更新
     */
    boolean updateAuthPermission(AuthPermission authPermissionRule);

    /**
     * 更新类型
     */
    @Update("update auth_permission set type = #{type} where id = #{id}")
    boolean updateAuthPermissionType(AuthPermission authPermissionRule);

    /**
     * 根据ids查询 规则名称
     */
    List<AuthPermission> listUrlAndRuleByIdIn(List<Long> ids);

}
