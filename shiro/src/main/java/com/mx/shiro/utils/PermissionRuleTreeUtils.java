package com.mx.shiro.utils;

import com.mx.shiro.entity.AuthPermission;
import com.mx.shiro.res.AuthPermissionMergeResponse;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限规则生成树形节点工具类
 */
public class PermissionRuleTreeUtils {

    /**
     * 多维数组
     */
    public static List<AuthPermissionMergeResponse> merge(List<AuthPermission> authPermissionRuleList,
                                                          Long pid) {

        //创建返回数组对象
        List<AuthPermissionMergeResponse> authPermissionMergeResponseList = new ArrayList<>();
        //遍历原数组
        for (AuthPermission v : authPermissionRuleList) {
            AuthPermissionMergeResponse authPermissionMergeResponse = new AuthPermissionMergeResponse();
            //将原数组的值复制给现在的数组
            BeanUtils.copyProperties(v, authPermissionMergeResponse);
            //判断其父节点
            if (pid.equals(v.getPid())) {
                //如果是则，将其添加到当前，并继续查询其子孩子
                authPermissionMergeResponse.setChildren(merge(authPermissionRuleList, v.getId()));
                authPermissionMergeResponseList.add(authPermissionMergeResponse);
            }
        }

        return authPermissionMergeResponseList;
    }


}
