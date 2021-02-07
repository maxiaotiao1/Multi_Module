package controller;

import com.mx.common.globalexception.ResultEnum;
import com.mx.common.res.ResultVOUtils;
import com.mx.shiro.annotation.AuthRuleAnnotation;
import com.mx.shiro.entity.AuthPermission;
import com.mx.shiro.req.AuthPermissionSaveRequest;
import com.mx.shiro.res.AuthPermissionMergeResponse;
import com.mx.shiro.service.AuthPermissionService;
import com.mx.shiro.utils.PermissionRuleTreeUtils;
import com.mx.shiro.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限规则相关
 */
@RestController
public class AuthPermissionController {

    @Autowired
    private AuthPermissionService authPermissionService;

    /**
     * 菜单及权限列表
     */
    @AuthRuleAnnotation("permission:index")
    @GetMapping("/admin/auth/permission_rule/index")
    public BaseResponse index() {

        List<AuthPermission> authPermissionRuleList = authPermissionService.listAll();
        List<AuthPermissionMergeResponse> merge = PermissionRuleTreeUtils.merge(authPermissionRuleList, 0L);

        Map<String, Object> restMap = new HashMap<>();
        restMap.put("list", merge);
        return ResultVOUtils.success(restMap);
    }

    /**
     * 新增
     */
    @AuthRuleAnnotation("permission:create")
    @PostMapping("/admin/auth/permission_rule/save")
    public BaseResponse save(@RequestBody @Valid AuthPermissionSaveRequest authPermissionSaveRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }
        //新增根节点
        if (authPermissionSaveRequest.getPid() == null) {
            authPermissionSaveRequest.setPid(0L); // 默认设置
        }

        AuthPermission authPermission = new AuthPermission();
        BeanUtils.copyProperties(authPermissionSaveRequest, authPermission);

        Result result = authPermissionService.insertAuthPermission(authPermission);
        if (Result.ERR == result.getCode()) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK,result.getMessage());
        }

        Map<String, Long> res = new HashMap<>();
        res.put("id", authPermission.getId());
        return ResultVOUtils.success(res);
    }

    /**
     * 编辑
     */
    @AuthRuleAnnotation("permission:edit")
    @PostMapping("/admin/auth/permission_rule/edit")
    public BaseResponse edit(@RequestBody @Valid AuthPermissionSaveRequest authPermissionSaveRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }
        if (authPermissionSaveRequest.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL);
        }

        authPermissionSaveRequest.setPid(null); // 不能修改父级 pid

        AuthPermission authPermissionRule = new AuthPermission();
        BeanUtils.copyProperties(authPermissionSaveRequest, authPermissionRule);

        boolean b = authPermissionService.updateAuthPermission(authPermissionRule);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }

    /**
     * 删除
     */
    @AuthRuleAnnotation("permission:delete")
    @PostMapping("/admin/auth/permission_rule/delete")
    public BaseResponse delete(@RequestBody AuthPermissionSaveRequest authPermissionSaveRequest) {

        if (authPermissionSaveRequest.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL);
        }

        boolean b = authPermissionService.deleteById(authPermissionSaveRequest.getId());
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }


}
