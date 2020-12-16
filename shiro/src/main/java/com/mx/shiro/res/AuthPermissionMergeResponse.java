package com.mx.shiro.res;

import lombok.Data;

import java.util.List;

/**
 * 权限列表整合为多维数组的视图
 */
@Data
public class AuthPermissionMergeResponse {

    private Long id;
    private Long pid;
    private String url;
    private String title;
    private Integer status;
    private Integer type;
    private String rule;
    private Integer listorder;

    // 一次性加载所有权限规则生成 tree 树形节点时需要
    private List<AuthPermissionMergeResponse> children;

}
