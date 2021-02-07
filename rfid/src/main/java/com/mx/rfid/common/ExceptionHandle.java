package com.mx.rfid.common;

import com.mx.common.globalexception.GlobalException;
import com.mx.common.globalexception.ResultEnum;
import com.mx.common.res.ApiResult;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * Created by chenliangliang on 2018/09/27.
 */
@RestControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(Exception.class)
    public ApiResult handle(Exception ex) {
        try {
            if (ex instanceof GlobalException) {
                GlobalException anyException = (GlobalException) ex;
                if (anyException.getCode().equals(ResultEnum.UNKONW_ERROR.getCode())) {
                    logger.error(anyException.getMessage(), ex);
                }
                return new ApiResult(anyException);
            } else if (ex instanceof ExpiredJwtException) {
                return new ApiResult(ResultEnum.TOKEN_INVALID);
            } else if (ex instanceof HttpRequestMethodNotSupportedException) {
                return new ApiResult(ResultEnum.METHOD_NOT_ALLOWED);
            } else if (ex instanceof HttpMediaTypeNotSupportedException) {
                return new ApiResult(ResultEnum.UNSUPPORTED_MEDIA_TYPE);
            } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
                return new ApiResult(ResultEnum.NOT_ACCEPTABLE);
            } else if (ex instanceof MissingPathVariableException) {
                return new ApiResult(ResultEnum.INTERNAL_SERVER_ERROR);
            } else if (ex instanceof MissingServletRequestParameterException) {
                return new ApiResult(ResultEnum.BAD_REQUEST);
            } else if (ex instanceof ServletRequestBindingException) {
                return new ApiResult(ResultEnum.BAD_REQUEST);
            } else if (ex instanceof ConversionNotSupportedException) {
                return new ApiResult(ResultEnum.INTERNAL_SERVER_ERROR);
            } else if (ex instanceof TypeMismatchException) {
                return new ApiResult(ResultEnum.BAD_REQUEST);
            } else if (ex instanceof HttpMessageNotReadableException) {
                return new ApiResult(ResultEnum.BAD_REQUEST);
            } else if (ex instanceof HttpMessageNotWritableException) {
                return new ApiResult(ResultEnum.INTERNAL_SERVER_ERROR);
            } else if (ex instanceof MethodArgumentNotValidException) {
                MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
                String result = dealBindingResult(exception.getBindingResult());
                return new ApiResult(ResultEnum.BAD_REQUEST.getCode(), result);
            } else if (ex instanceof MissingServletRequestPartException) {
                return new ApiResult(ResultEnum.BAD_REQUEST);
            } else if (ex instanceof BindException) {
                BindException exception = (BindException) ex;
                String result = dealBindingResult(exception.getBindingResult());
                return new ApiResult(ResultEnum.BAD_REQUEST.getCode(), result);
            } else if (ex instanceof NoHandlerFoundException) {
                return new ApiResult(ResultEnum.NOT_FOUND);
            } else if (ex instanceof ConstraintViolationException) {
                throw ex;
            } else {
                if (logger.isWarnEnabled()) {
                    logger.warn("Unknown exception type: " + ex.getClass().getName());
                }
                logger.error(ex.getMessage(), ex);
                return new ApiResult(ResultEnum.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            logger.error(ex.getMessage(), ex);
            return new ApiResult(ResultEnum.UNKONW_ERROR);
        }
    }

    private String dealBindingResult(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            sb.append(fieldError.getField())
                    .append(":")
                    .append(fieldError.getDefaultMessage())
                    .append(";");
        });
        return sb.toString();
    }
}
