package controller;

import com.github.pagehelper.PageInfo;
import com.mx.common.enums.ResultEnum;
import com.mx.common.res.BaseResponse;
import com.mx.common.res.PageSimpleResponse;
import com.mx.common.res.ResultVOUtils;
import com.mx.shiro.annotation.AuthRuleAnnotation;
import com.mx.shiro.entity.AuthRolePermission;
import com.mx.shiro.entity.AuthPermission;
import com.mx.shiro.entity.AuthRole;
import com.mx.shiro.req.AuthRoleAuthRequest;
import com.mx.shiro.req.AuthRoleQueryRequest;
import com.mx.shiro.req.AuthRoleSaveRequest;
import com.mx.shiro.res.AuthPermissionMergeResponse;
import com.mx.shiro.res.AuthRoleResponse;
import com.mx.shiro.service.AuthPermissionService;
import com.mx.shiro.service.AuthRolePermissionService;
import com.mx.shiro.service.AuthRoleService;
import com.mx.shiro.utils.PermissionRuleTreeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色相关
 */
@RestController
public class AuthRoleController {

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    /**
     * 角色列表
     */
    @AuthRuleAnnotation("role:index")
    @GetMapping("/admin/auth/role/index")
    public BaseResponse index(@Valid AuthRoleQueryRequest authRoleQueryRequest,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }
        //查询角色
        List<AuthRole> authRoleList = authRoleService.listAdminPage(authRoleQueryRequest);
        List<AuthRoleResponse> authRoleResponseList = authRoleList.stream().map(item -> {
            AuthRoleResponse authRoleResponse = new AuthRoleResponse();
            BeanUtils.copyProperties(item, authRoleResponse);
            return authRoleResponse;
        }).collect(Collectors.toList());

        PageInfo<AuthRole> pageInfo = new PageInfo<>(authRoleList);
        PageSimpleResponse<AuthRoleResponse> pageSimpleResponse = new PageSimpleResponse<>();
        pageSimpleResponse.setTotal(pageInfo.getTotal());
        pageSimpleResponse.setList(authRoleResponseList);
        return ResultVOUtils.success(pageSimpleResponse);
    }

    /**
     * 获取授权列表
     */
    @AuthRuleAnnotation("role:index")
    @GetMapping("/admin/auth/role/authList")
    public BaseResponse authList(@RequestParam("id") Long id) {

        // 查询当前角色拥有的权限id
        List<AuthRolePermission> authPermissionList = authRolePermissionService.listByRoleId(id);
        List<Long> checkedKeys = authPermissionList.stream()
                .map(AuthRolePermission::getPermissionId)
                .collect(Collectors.toList());

        // 查询所有权限规则
        List<AuthPermission> authPermissionRuleList = authPermissionService.listAll();
        List<AuthPermissionMergeResponse> merge = PermissionRuleTreeUtils.merge(authPermissionRuleList, 0L);

        Map<String, Object> restMap = new HashMap<>();
        restMap.put("list", merge);
        restMap.put("checkedKeys", checkedKeys);
        return ResultVOUtils.success(restMap);
    }

    //角色授权
    @AuthRuleAnnotation("role:permission")
    @PostMapping("/admin/auth/role/auth")
    public BaseResponse auth(@RequestBody @Valid AuthRoleAuthRequest authRoleAuthRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        // 先删除之前的授权
        authRolePermissionService.deleteByRoleId(authRoleAuthRequest.getRoleId());

        List<AuthRolePermission> authPermissionList = authRoleAuthRequest.getAuthRules().stream()
                .map(aLong -> {
                    AuthRolePermission authPermission = new AuthRolePermission();
                    authPermission.setRoleId(authRoleAuthRequest.getRoleId());
                    authPermission.setPermissionId(aLong);
                    authPermission.setType("admin");
                    return authPermission;
                }).collect(Collectors.toList());

        int i = authRolePermissionService.insertAuthPermissionAll(authPermissionList);

        return ResultVOUtils.success();
    }

    /**
     * 新增
     */
    @AuthRuleAnnotation("role:create")
    @PostMapping("/admin/auth/role/save")
    public BaseResponse save(@RequestBody @Valid AuthRoleSaveRequest authRoleSaveRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        AuthRole byName = authRoleService.findByName(authRoleSaveRequest.getName());
        if (byName != null) {
            return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "当前角色已存在");
        }

        AuthRole authRole = new AuthRole();
        BeanUtils.copyProperties(authRoleSaveRequest, authRole);

        boolean b = authRoleService.insertAuthRole(authRole);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        Map<String, Long> res = new HashMap<>();
        res.put("id", authRole.getId());
        return ResultVOUtils.success(res);
    }

    /**
     * 编辑
     */
    @AuthRuleAnnotation("role:edit")
    @PostMapping("/admin/auth/role/edit")
    public BaseResponse edit(@RequestBody @Valid AuthRoleSaveRequest authRoleSaveRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (authRoleSaveRequest.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL);
        }

        // 检查是否存在当前角色
        AuthRole byName = authRoleService.findByName(authRoleSaveRequest.getName());
        if (byName != null && !authRoleSaveRequest.getId().equals(byName.getId())) {
            return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "当前角色已存在");
        }

        AuthRole authRole = new AuthRole();
        BeanUtils.copyProperties(authRoleSaveRequest, authRole);

        boolean b = authRoleService.updateAuthRole(authRole);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }

    /**
     * 删除
     */
    @AuthRuleAnnotation("role:delete")
    @PostMapping("/admin/auth/role/delete")
    public BaseResponse delete(@RequestBody AuthRoleSaveRequest authRoleSaveRequest) {

        if (authRoleSaveRequest.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL);
        }

        boolean b = authRoleService.deleteById(authRoleSaveRequest.getId());
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        //TODO 删除角色后先前授权的缓存不会消失

        // 再删除之前的授权
        authRolePermissionService.deleteByRoleId(authRoleSaveRequest.getId());

        return ResultVOUtils.success();
    }


}
