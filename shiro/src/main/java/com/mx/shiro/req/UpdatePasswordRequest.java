package com.mx.shiro.req;

import lombok.Data;

/**
 * 修改密码的表单
 */
@Data
public class UpdatePasswordRequest {

    //@NotNull(message = "参数错误！")
    private Long adminId;

    //@NotEmpty(message = "请输入旧密码")
    private String oldPassword;

    //@NotEmpty(message = "请输入新密码")
    private String newPassword;

    public Long getAdminId() {
        return adminId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
