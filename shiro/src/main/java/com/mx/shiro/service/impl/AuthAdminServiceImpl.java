package com.mx.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.mx.common.globalexception.ResultEnum;
import com.mx.common.globalexception.GlobalException;
import com.mx.shiro.dao.AuthAdminDao;
import com.mx.shiro.entity.AuthAdmin;
import com.mx.shiro.req.AuthAdminQueryRequest;
import com.mx.shiro.service.AuthAdminService;
import com.mx.shiro.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class AuthAdminServiceImpl implements AuthAdminService {

    @Autowired
    private AuthAdminDao authAdminDao;

    @Override
    public List<AuthAdmin> listAdminPage(AuthAdminQueryRequest authAdminQueryRequest) {
        if (authAdminQueryRequest == null) {
            return Collections.emptyList();
        }
        int offset = (authAdminQueryRequest.getPage() - 1) * authAdminQueryRequest.getLimit();
        PageHelper.offsetPage(offset, authAdminQueryRequest.getLimit());
        return authAdminDao.listAdminPage(authAdminQueryRequest);
    }

    @Override
    public AuthAdmin findByUserName(String userName) {
        return authAdminDao.findByUserName(userName);
    }

    /**
     * 根据id 获取需要的info
     *
     * @param id
     * @return
     */
    @Override
    public AuthAdmin findById(Long id) {
        return authAdminDao.findById(id);
    }

    /**
     * 根据 id 获取密码字段
     *
     * @param id
     * @return
     */
    @Override
    public AuthAdmin findPwdById(Long id) {
        return authAdminDao.findPwdById(id);
    }

    /**
     * 新增
     *
     * @param authAdmin
     * @return
     */
    @Override
    public Result insertAuthAdmin(AuthAdmin authAdmin) {

        if (authAdmin.getUsername() != null) {
            AuthAdmin byUserName = authAdminDao.findByUserName(authAdmin.getUsername());
            if (byUserName != null) {
                return Result.error("当前管理员已存在");
            }
        }
        authAdmin.setCreateTime(new Date());
        if ( !authAdminDao.insertAuthAdmin(authAdmin) ){
            return Result.error("插入失败");
        }
        return Result.success();
    }

    /**
     * 更新
     *
     * @param authAdmin
     * @return
     */
    @Override
    public boolean updateAuthAdmin(AuthAdmin authAdmin) {

        if (authAdmin.getId() == null) {
            return false;
        }
        // 当用户名不为空时，检查是否存在
        if (authAdmin.getUsername() != null) {
            AuthAdmin byUserName = authAdminDao.findByUserName(authAdmin.getUsername());
            // 判断是否存在，剔除自己
            if (byUserName != null && !authAdmin.getId().equals(byUserName.getId())) {
                throw new GlobalException(ResultEnum.DATA_REPEAT, "当前管理员已存在");
            }
        }

        return authAdminDao.updateAuthAdmin(authAdmin);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        return authAdminDao.deleteById(id);
    }


}
