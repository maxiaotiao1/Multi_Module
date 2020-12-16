package com.mx.shiro.res;

import lombok.Data;

import java.util.List;

/**
 * 登录用户的信息视图
 */
@Data
public class LoginUserInfoResponse {

    private Long id;
    private String username;
    private String avatar;
    //菜单列表
    private List<String> menuPath;
    //权限列表
    private List<String> authRules;

}
