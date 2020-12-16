package com.mx.shiro.service;


import com.mx.shiro.entity.AuthAdmin;
import com.mx.shiro.req.AuthAdminQueryRequest;
import com.mx.shiro.utils.Result;

import java.util.List;

public interface AuthAdminService {

    List<AuthAdmin> listAdminPage(AuthAdminQueryRequest authAdminQueryRequest);

    AuthAdmin findByUserName(String userName);


    AuthAdmin findById(Long id);


    AuthAdmin findPwdById(Long id);

    Result insertAuthAdmin(AuthAdmin authAdmin);

    boolean updateAuthAdmin(AuthAdmin authAdmin);

    boolean deleteById(Long id);

}
