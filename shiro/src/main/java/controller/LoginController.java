package controller;

import com.mx.common.enums.ResultEnum;
import com.mx.common.exception.JsonException;
import com.mx.common.res.BaseResponse;
import com.mx.common.res.ResultVOUtils;
import com.mx.shiro.entity.AuthAdmin;
import com.mx.shiro.req.LoginRequest;
import com.mx.shiro.req.UpdatePasswordRequest;
import com.mx.shiro.res.LoginUserInfoResponse;
import com.mx.shiro.service.AuthAdminService;
import com.mx.shiro.service.AuthLoginService;
import com.mx.shiro.utils.IpUtils;
import com.mx.shiro.utils.JwtUtils;
import com.mx.shiro.utils.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录相关
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private AuthLoginService authLoginService;

    @Autowired
    private AuthAdminService authAdminService;


    /**
     * 用户登录
     *
     * @return
     */
    @PostMapping(value = "/admin/auth/login/index")
    public BaseResponse index(@RequestBody @Valid LoginRequest loginRequest,
                              BindingResult bindingResult,
                              HttpServletRequest request) throws Exception {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        Date now = new Date();

        AuthAdmin authAdmin = authAdminService.findByUserName(loginRequest.getUserName());
        if (authAdmin == null) {
            throw new JsonException(ResultEnum.DATA_NOT, "用户名或密码错误");
        }

        if (!PasswordUtils.authAdminPwd(loginRequest.getPwd()).equals(authAdmin.getPassword())) {
            throw new JsonException(ResultEnum.DATA_NOT, "用户名或密码错误");
        }

        // 更新登录状态
        AuthAdmin authAdminUp = new AuthAdmin();
        authAdminUp.setId(authAdmin.getId());
        authAdminUp.setLastLoginTime(new Date());
        authAdminUp.setLastLoginIp(IpUtils.getIpAddr(request));
        authAdminService.updateAuthAdmin(authAdminUp);

        // 登录成功后获取权限，这里面会设置到缓存
        authLoginService.listRuleByAdminId(authAdmin.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("admin_id", authAdmin.getId());
        String token = JwtUtils.createToken(claims, 86400L); // 一天后过期

        Map<String, Object> map = new HashMap<>();
        map.put("id", authAdmin.getId());
        map.put("token", token);

        return ResultVOUtils.success(map);
    }

    /**
     * 获取登录用户信息
     */
    @GetMapping("/admin/auth/login/userInfo")
    public BaseResponse userInfo(HttpServletRequest request) {
        String adminId = request.getHeader("X-Adminid");
        Long id = Long.valueOf(adminId);

        AuthAdmin authAdmin = authAdminService.findById(id);
        //获取权限列表
        List<String> authRules = authLoginService.listRuleByAdminId(authAdmin.getId());
        //获取菜单列表
        List<String> menuPath = authLoginService.listUrlByAdminId(authAdmin.getId());

        LoginUserInfoResponse loginUserInfoResponse = new LoginUserInfoResponse();
        BeanUtils.copyProperties(authAdmin, loginUserInfoResponse);
        loginUserInfoResponse.setAuthRules(authRules);
        loginUserInfoResponse.setMenuPath(menuPath);

        return ResultVOUtils.success(loginUserInfoResponse);
    }

    /**
     * 登出
     *
     * @return
     */
    @PostMapping("/admin/auth/login/out")
    public BaseResponse out() {
        return ResultVOUtils.success();
    }

    /**
     * 修改密码
     *
     * @return
     */
    @PostMapping("/admin/auth/login/password")
    public BaseResponse password(@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        AuthAdmin authAdmin = authAdminService.findPwdById(updatePasswordRequest.getAdminId());
        if (authAdmin == null) {
            throw new JsonException(ResultEnum.DATA_NOT);
        }
        String oldPwd = PasswordUtils.authAdminPwd(updatePasswordRequest.getOldPassword());
        // 旧密码不对
        if (authAdmin.getPassword() != null
                && !authAdmin.getPassword().equals(oldPwd)) {
            throw new JsonException(ResultEnum.DATA_NOT, "旧密码匹配失败");
        }

        AuthAdmin authAdminUp = new AuthAdmin();
        authAdminUp.setId(updatePasswordRequest.getAdminId());
        String newPwd = PasswordUtils.authAdminPwd(updatePasswordRequest.getNewPassword());
        authAdminUp.setPassword(newPwd);

        boolean b = authAdminService.updateAuthAdmin(authAdminUp);
        if (b) {
            return ResultVOUtils.success();
        }

        return ResultVOUtils.error(ResultEnum.DATA_CHANGE);
    }

}
