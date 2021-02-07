package com.mx.common.res;
import com.mx.common.globalexception.ResultEnum;

/**
 * @author LiTao
 * @packageName com.lehu.framework.controller
 * @className BaseController
 * @time 10:37
 * @date 2019/5/27
 */
public class BaseController {

    protected final <T> ApiResult success() {
        return new ApiResult<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }

    protected final <T> ApiResult success(String message,final T data) {
        return new ApiResult<T>(ResultEnum.SUCCESS.getCode(), message,data);
    }

    protected final <T> ApiResult<T> success(final T data) {
        return new ApiResult<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    protected final <T> ApiResult<T> successResponse(final T data, String expandKeys) {
        return new ApiResult<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data, expandKeys);
    }

    protected final <T> ApiResult<T> userError(final T data) {
        return new ApiResult<>(ResultEnum.USER_UNFIND.getCode(), ResultEnum.USER_UNFIND.getMessage(), data);
    }

    protected final <T> ApiResult<T> userNameError(final T data) {
        return new ApiResult<>(ResultEnum.PHONE_ERROR.getCode(), ResultEnum.PHONE_ERROR.getMessage(), data);
    }

    protected final <T> ApiResult<T> delctError(final T data) {
        return new ApiResult<>(ResultEnum.USER_UNFIND.getCode(), ResultEnum.USER_UNFIND.getMessage(), data);
    }

    protected final <T> ApiResult<T> roleNameErro(final T data) {
        return new ApiResult<>(ResultEnum.ROLE_NAME.getCode(), ResultEnum.ROLE_NAME.getMessage(), data);
    }

    protected final <T> ApiResult<T> nameExistErro(final T data) {
        return new ApiResult<>(ResultEnum.NAME_EXIST_ERRO.getCode(), ResultEnum.NAME_EXIST_ERRO.getMessage(), data);
    }

    protected final <T> ApiResult<T> vitificationTimeErro() {
        return new ApiResult<>(ResultEnum.VITIFICATION_TIME.getCode(), ResultEnum.VITIFICATION_TIME.getMessage());
    }

    protected final <T> ApiResult<T> repeatErro() {
        return new ApiResult<>(ResultEnum.REPEAT_NUM.getCode(), ResultEnum.REPEAT_NUM.getMessage());
    }

    protected final <T> ApiResult<T> noBinding() {
        return new ApiResult<>(ResultEnum.NO_BINDING.getCode(), ResultEnum.NO_BINDING.getMessage());
    }

    protected final <T> ApiResult<T> commentErro() {
        return new ApiResult<>(ResultEnum.COMMENT_ERRO.getCode(), ResultEnum.COMMENT_ERRO.getMessage());
    }

//	protected final <T> ApiResult<T> nickNameExistErro(final T data) {
//		return new ApiResult<>(ResultEnum.NICKNAME_EXIST_ERRO.getCode(), ResultEnum.NICKNAME_EXIST_ERRO.getMessage(),
//				data);
//	}

    protected final <T> ApiResult<T> noUserName(final T data) {
        return new ApiResult<>(ResultEnum.NO_NUSER_NAME.getCode(), ResultEnum.NO_NUSER_NAME.getMessage(), data);
    }

    protected final <T> ApiResult<T> vitification(final T data) {
        return new ApiResult<>(ResultEnum.VITIFICATION_ERRO.getCode(), ResultEnum.VITIFICATION_ERRO.getMessage(), data);
    }

    protected final <T> ApiResult<T> PasswordError(final T data) {
        return new ApiResult<>(ResultEnum.PASSWORD_ERRO.getCode(), ResultEnum.PASSWORD_ERRO.getMessage(), data);
    }

    protected final <T> ApiResult<T> MaterialError(final T data) {
        return new ApiResult<>(ResultEnum.MATERIAL_ERRO.getCode(), ResultEnum.MATERIAL_ERRO.getMessage(), data);
    }

    protected final <T> ApiResult<T> vitificationOutTime(final T data) {
        return new ApiResult<>(ResultEnum.VITIFICATION_OUT_ERRO.getCode(), ResultEnum.VITIFICATION_OUT_ERRO.getMessage(), data);
    }

    protected final <T> ApiResult<T> loginErro(final T data) {
        return new ApiResult<>(ResultEnum.LOGIN_ERRO.getCode(), ResultEnum.LOGIN_ERRO.getMessage(), data);
    }

    protected final <T> ApiResult<T> noWxLogin() {
        return new ApiResult<>(ResultEnum.NO_WX_LOGIN.getCode(), ResultEnum.NO_WX_LOGIN.getMessage());
    }

    protected final <T> ApiResult<T> noWbLogin() {
        return new ApiResult<>(ResultEnum.NO_WB_LOGIN.getCode(), ResultEnum.NO_WB_LOGIN.getMessage());
    }

    protected final <T> ApiResult<T> isLogin(final T data) {
        return new ApiResult<>(ResultEnum.IS_LOGIN.getCode(), ResultEnum.IS_LOGIN.getMessage(), data);
    }

    protected final <T> ApiResult<T> failLogin(final T data) {
        return new ApiResult<>(ResultEnum.FIAL_LOGIN.getCode(), ResultEnum.FIAL_LOGIN.getMessage(), data);
    }

    protected final <T> ApiResult<T> error(final ResultEnum ResultEnum, final T data) {
        return new ApiResult<>(ResultEnum.getCode(), ResultEnum.getMessage(), data);
    }

    protected final <T> ApiResult<T> addAlbumError() {
        return new ApiResult<>(ResultEnum.ALBUM_EXIST_ERRO.getCode(), ResultEnum.ALBUM_EXIST_ERRO.getMessage());
    }

    protected final ApiResult error(final String msg) {
        return new ApiResult(ResultEnum.DELECT.getCode(), msg, msg);
    }

    protected final ApiResult integral_error(final String msg) {
        return new ApiResult(ResultEnum.INTEGRAL_ERROR.getCode(), msg, msg);
    }

    protected final ApiResult album_error(final String msg) {
        return new ApiResult(ResultEnum.ALBUM_ERROR.getCode(), msg, msg);
    }


    protected final <T> ApiResult<T> PROJECT_DELETE(T data) {
        return new ApiResult<T>(ResultEnum.PROJECT_DELETE.getCode(), ResultEnum.PROJECT_DELETE.getMessage(), data);
    }

    protected final <T> ApiResult<T> TOKEN_INVALID(T data) {
        return new ApiResult<T>(ResultEnum.TOKEN_INVALID.getCode(), ResultEnum.TOKEN_INVALID.getMessage(), data);
    }


    protected final <T> ApiResult<T> registerError(String message) {
        return new ApiResult<>(500, message, null);
    }

    protected final <T> ApiResult<T> addPostSuccess(String message) {
        return new ApiResult<>(200, message, null);
    }

    protected final <T> ApiResult<T> addObjectError(String message) {
        return new ApiResult<T>(500, message, null);
    }

    protected final <T> ApiResult<T> updatePostSuccess(String message) {
        return new ApiResult<>(200, message, null);
    }

    protected final <T> ApiResult<T> updatePostError(String message) {
        return new ApiResult<>(500, message, null);
    }


    protected final <T> ApiResult<T> updateObjectError(String message) {
        return new ApiResult<T>(500, message, null);
    }

    protected final <T> ApiResult<T> deletePostSuccess(String message) {
        return new ApiResult<>(200, message, null);
    }

    protected final <T> ApiResult<T> deleteObjectError(String message) {
        return new ApiResult<T>(500, message, null);
    }

    protected final <T> ApiResult<T> ErrorDetail(String message) {
        return new ApiResult<T>(500, message, null);
    }


    protected final <T> ApiResult<T> getObjectSuccess(String message, final T data) {
        return new ApiResult<T>(200, message, data);
    }

    protected final <T> ApiResult<T> addObjectSuccess(String message, final T data) {
        return new ApiResult<>(200, message, data);
    }

    protected final <T> ApiResult<T> loginPersonStatus(final T data) {
        return new ApiResult<>(ResultEnum.LOGIN_PersonStatus.getCode(), ResultEnum.LOGIN_PersonStatus.getMessage(), data);
    }

    protected final <T> ApiResult<T> loginSuccess(final T data) {
        return new ApiResult<>(ResultEnum.LOGIN_SUCCESS.getCode(), ResultEnum.LOGIN_SUCCESS.getMessage(), data);
    }

    //	protected final <T> ApiResult<T> success(final T data) {
    protected final <T> ApiProvideResult<T> provideSuccess(final T data) {
        return new ApiProvideResult<>(ResultEnum.PROVIDE_LOGINACCOUNTSUCCESS.getCode(), ResultEnum.PROVIDE_LOGINACCOUNTSUCCESS.getMessage(), data);
    }


    protected final <T> ApiProvideResult provideAccountError(final T data) {
        return new ApiProvideResult<>(ResultEnum.PROVIDE_LOGINACCOUNTNULL.getCode(), ResultEnum.PROVIDE_LOGINACCOUNTNULL.getMessage(), data);
    }

    protected final <T> ApiProvideResult providePasswordError(final T data) {
        return new ApiProvideResult<>(ResultEnum.PROVIDE_LOGINPASSWORDNULL.getCode(), ResultEnum.PROVIDE_LOGINPASSWORDNULL.getMessage(), data);
    }

    protected final <T> ApiProvideResult provideError(final T data) {
        return new ApiProvideResult<>(ResultEnum.PROVIDE_LOGINERROE.getCode(), ResultEnum.PROVIDE_LOGINERROE.getMessage(), data);
    }

    protected final <T> ApiProvideResult provideError(final T data, final String message) {
        return new ApiProvideResult<>(ResultEnum.PROVIDE_LOGINPASSWORDNULL.getCode(), message, data);
    }
//    protected final <T> ApiProvideResult myDefineError(final Integer code,String data, final String message) {
//        return new ApiProvideResult<>(code, data, message);
//    }

    protected final <T> ApiResult<T> myDefineError(Integer code,final T data, final String message) {
        return new ApiResult<>(code, message, data);
    }
}
