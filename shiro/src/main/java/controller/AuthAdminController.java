package controller;

import com.github.pagehelper.PageInfo;
import com.mx.common.globalexception.ResultEnum;
import com.mx.common.res.PageSimpleResponse;
import com.mx.common.res.ResultVOUtils;
import com.mx.shiro.annotation.AuthRuleAnnotation;
import com.mx.shiro.entity.AuthAdmin;
import com.mx.shiro.entity.AuthRole;
import com.mx.shiro.entity.AuthRoleAdmin;
import com.mx.shiro.req.AuthAdminQueryRequest;
import com.mx.shiro.req.AuthAdminSaveRequest;
import com.mx.shiro.res.AuthAdminResponse;
import com.mx.shiro.res.AuthAdminRoleResponse;
import com.mx.shiro.service.AuthAdminService;
import com.mx.shiro.service.AuthRoleAdminService;
import com.mx.shiro.service.AuthRoleService;
import com.mx.shiro.utils.PasswordUtils;
import com.mx.shiro.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 管理员相关
 */
@RestController
public class AuthAdminController {

    @Autowired
    private AuthAdminService authAdminService;

    @Autowired
    private AuthRoleService authRoleService;

    @Resource
    private AuthRoleAdminService authRoleAdminService;

    /**
     * 获取管理员列表
     */
    @AuthRuleAnnotation("user:index")
    @GetMapping("/admin/auth/admin/index")
    public BaseResponse index(@Valid AuthAdminQueryRequest authAdminQueryRequest,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (authAdminQueryRequest.getRoleId() != null) {
            List<AuthRoleAdmin> authRoleAdmins = authRoleAdminService.listByRoleId(authAdminQueryRequest.getRoleId());
            List<Long> ids = new ArrayList<>();
            if (authRoleAdmins != null && !authRoleAdmins.isEmpty()) {
                ids = authRoleAdmins.stream().map(AuthRoleAdmin::getAdminId).collect(Collectors.toList());
            }
            authAdminQueryRequest.setIds(ids);
        }
        List<AuthAdmin> authAdminList = authAdminService.listAdminPage(authAdminQueryRequest);

        // 查询所有的权限
        List<Long> adminIds = authAdminList.stream().map(AuthAdmin::getId).collect(Collectors.toList());
        List<AuthRoleAdmin> authRoleAdminList = authRoleAdminService.listByAdminIdIn(adminIds);

        // 视图列表
        List<AuthAdminResponse> authAdminResponseList = authAdminList.stream().map(item -> {
            AuthAdminResponse authAdminResponse = new AuthAdminResponse();
            BeanUtils.copyProperties(item, authAdminResponse);
            List<Long> roles = authRoleAdminList.stream()
                    .filter(authRoleAdmin -> authAdminResponse.getId().equals(authRoleAdmin.getAdminId()))
                    .map(AuthRoleAdmin::getRoleId)
                    .collect(Collectors.toList());
            authAdminResponse.setRoles(roles);
            return authAdminResponse;
        }).collect(Collectors.toList());

        PageInfo<AuthAdmin> authAdminPageInfo = new PageInfo<>(authAdminList);
        PageSimpleResponse<AuthAdminResponse> authAdminPageSimpleResponse = new PageSimpleResponse<>();
        authAdminPageSimpleResponse.setTotal(authAdminPageInfo.getTotal());
        authAdminPageSimpleResponse.setList(authAdminResponseList);

        return ResultVOUtils.success(authAdminPageSimpleResponse);

    }
    /**
     * 获取角色列表
     */
    @AuthRuleAnnotation("user:index")
    @GetMapping("/admin/auth/admin/roleList")
    public BaseResponse roleList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "100") Integer limit) {

        List<AuthRole> authRoleList = authRoleService.listAuthAdminRolePage(page, limit, null);
        PageInfo<AuthRole> pageInfo = new PageInfo<>(authRoleList);
        PageSimpleResponse<AuthAdminRoleResponse> pageSimpleResponse = new PageSimpleResponse<>();
        pageSimpleResponse.setTotal(pageInfo.getTotal());
        List<AuthAdminRoleResponse> authAdminRoleResponses = authRoleList.stream().map(e -> {
            AuthAdminRoleResponse authAdminRoleResponse = new AuthAdminRoleResponse();
            BeanUtils.copyProperties(e, authAdminRoleResponse);
            return authAdminRoleResponse;
        }).collect(Collectors.toList());
        pageSimpleResponse.setList(authAdminRoleResponses);

        return ResultVOUtils.success(pageSimpleResponse);

    }


    /**
     * 新增
     *
     * @return
     */
    @AuthRuleAnnotation("user:create")
    @PostMapping("/admin/auth/admin/save")
    public BaseResponse save(@RequestBody @Valid AuthAdminSaveRequest authAdminSaveRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        // 检查是否存在相同名称的管理员
        AuthAdmin byUserName = authAdminService.findByUserName(authAdminSaveRequest.getUsername());
        if (byUserName != null) {
            return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "当前管理员已存在");
        }

        AuthAdmin authAdmin = new AuthAdmin();
        BeanUtils.copyProperties(authAdminSaveRequest, authAdmin);

        if (authAdmin.getPassword() != null) {
            authAdmin.setPassword(PasswordUtils.authAdminPwd(authAdmin.getPassword()));
        }

        Result result = authAdminService.insertAuthAdmin(authAdmin);

        if (result.getCode() != Result.SUCCESS) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
        // 插入角色
        if (authAdminSaveRequest.getRoles() != null) {
            authRoleAdminService.insertRolesAdminIdAll(authAdminSaveRequest.getRoles(), authAdmin.getId());
        }

        Map<String, Long> res = new HashMap<>();
        res.put("id", authAdmin.getId());
        return ResultVOUtils.success(res);
    }

    /**
     * 修改
     */
    @AuthRuleAnnotation("user:edit")
    @PostMapping("/admin/auth/admin/edit")
    public BaseResponse edit(@RequestBody @Valid AuthAdminSaveRequest authAdminSaveRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (authAdminSaveRequest.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误！");
        }

        // 检查是否存在除了当前管理员的其它名称的管理员
        AuthAdmin byUserName = authAdminService.findByUserName(authAdminSaveRequest.getUsername());
        if (byUserName != null && !authAdminSaveRequest.getId().equals(byUserName.getId())) {
            return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "当前管理员已存在");
        }

        AuthAdmin authAdmin = new AuthAdmin();
        BeanUtils.copyProperties(authAdminSaveRequest, authAdmin);
        if (authAdmin.getPassword() != null) {
            authAdmin.setPassword(PasswordUtils.authAdminPwd(authAdmin.getPassword()));
        }

        boolean b = authAdminService.updateAuthAdmin(authAdmin);

        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        // 修改角色
        if (authAdminSaveRequest.getRoles() != null) {
            // 先删除之前的
            authRoleAdminService.deleteByAdminId(authAdmin.getId());
            authRoleAdminService.insertRolesAdminIdAll(authAdminSaveRequest.getRoles(), authAdmin.getId());
        }
        return ResultVOUtils.success();
    }

    /**
     * 删除
     */
    @AuthRuleAnnotation("user:delete")
    @PostMapping("/admin/auth/admin/delete")
    public BaseResponse delete(@RequestBody AuthAdminSaveRequest authAdminSaveRequest) {

        if (authAdminSaveRequest.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误！");
        }

        boolean b = authAdminService.deleteById(authAdminSaveRequest.getId());
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
        // 先删除之前的角色
        authRoleAdminService.deleteByAdminId(authAdminSaveRequest.getId());

        return ResultVOUtils.success();
    }


}
