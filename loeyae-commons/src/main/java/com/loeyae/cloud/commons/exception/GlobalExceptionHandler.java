package com.loeyae.cloud.commons.exception;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.tool.JsonTool;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GlobalExceptionHandler
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/6 18:24
 */
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory
            .getLogger(GlobalExceptionHandler.class);

    /**
     * <p>
     * 自定义 REST 业务异常
     * <p>
     *
     * @param e 异常类型
     * @return ApiResult
     */
    @ExceptionHandler(value = Exception.class)
    public ApiResult handleBadRequest(Exception e) {
        ApiResult r = new ApiResult(BaseErrorCode.FAILED);

        if (e instanceof ValidateException) {
            IErrorCode errorCode = ((ValidateException) e).getErrorCode();
            if (null != errorCode) {
                r.setCode(errorCode.getCode());
            } else {
                r.setCode(BaseErrorCode.VALIDATE_FAIL.getCode());
            }
            r.setMsg(((ValidateException) e).getValidateMessage());
            return r;
        }

        /*
         * 业务逻辑警告提示
         */
        if (e instanceof GlobalException) {
            IErrorCode errorCode = ((GlobalException) e).getErrorCode();
            logger.error("Rest request error, {}", e.getMessage());
            if (null != errorCode) {
                r.setCode(errorCode.getCode());
                r.setMsg(errorCode.getMsg());
                if (errorCode.getData() != null) {
                    r.setData(errorCode.getData());
                }
                return r;
            }
            r.setMsg(e.getMessage());
            return r;
        }

        /*
         * feign调用异常
         */
        if (e instanceof FeignException) {
            ApiResult feignApiResult = ((FeignException) e).getApiResult();
            logger.error("Rest request error, {}", feignApiResult.getMsg());
            return feignApiResult;
        }

        /*
         * ApiResultException
         */
        if (e instanceof ApiResultException) {
            ApiResult apiResult = ((ApiResultException) e).getApiResult();
            logger.error("Rest request error, {}", apiResult.getMsg());
            return apiResult;
        }

        /*
         * feign熔断异常
         */
        if (e instanceof HystrixRuntimeException) {
            HystrixRuntimeException hystrixRuntimeException = (HystrixRuntimeException) e;
            FeignError error = new FeignError();
            error.setDetail(hystrixRuntimeException.toString());
            Throwable cause = hystrixRuntimeException.getCause();
            error.setCause(cause.toString());
            error.setBase(hystrixRuntimeException.getFallbackException().toString());
            if (cause.getCause() == null) {
                error.setCauseDetail(cause.toString());
            } else {
                error.setCauseDetail(hystrixRuntimeException.getCause().getCause().toString());
            }
            ApiResult apiResult = new ApiResult();
            apiResult.setCode(BaseErrorCode.FEIGN_FAIL.getCode());
            apiResult.setMsg(error.toString());
            return apiResult;
        }

        /*
         * 参数校验异常
         */
        if (e instanceof BindException) {
            BindingResult bindingResult = ((BindException) e)
                    .getBindingResult();
            if (null != bindingResult && bindingResult.hasErrors()) {
                List<Object> jsonList = new ArrayList<>();
                bindingResult.getFieldErrors().stream().forEach(fieldError -> {
                    Map<String, Object> jsonObject = new HashMap<>(2);
                    jsonObject.put("name", fieldError.getField());
                    jsonObject.put("msg", fieldError.getDefaultMessage());
                    jsonList.add(jsonObject);
                });
                r.setMsg(JsonTool.beanToJson(jsonList));
                return r;
            }
        }

        logger.error("Error: System Exception", e);

        /**
         * 系统内部异常，打印异常栈
         */
        String msg = JsonTool.beanToJson(e.toString());
        r.setMsg(msg);
        return customEnd(e, r);
    }

    public ApiResult customEnd(Exception e, ApiResult r) {
        return r;
    }
}