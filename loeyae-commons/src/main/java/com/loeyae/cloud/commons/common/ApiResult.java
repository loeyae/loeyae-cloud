package com.loeyae.cloud.commons.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.loeyae.cloud.commons.exception.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

/**
 * ApiResult
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/6 18:25
 */
@Data
public class ApiResult<T> implements Serializable {

    /**
     * 返回码
     */
    private long code;

    /**
     * 描述
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public ApiResult(IErrorCode errorCode) {
        errorCode = Optional.ofNullable(errorCode).orElse(BaseErrorCode.FAILED);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public ApiResult() {

    }

    public static <T> ApiResult<T> ok() {
        return ok(null);
    }

    public static <T> ApiResult<T> ok(T data) {
        BaseErrorCode aec = BaseErrorCode.SUCCESS;
//        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
//            aec = BaseErrorCode.FAILED;
//        }
        return restResult(data, aec);
    }

    public static <T> ApiResult<T> failed(String msg) {
        return restResult(null, BaseErrorCode.FAILED.getCode(), msg);
    }

    public static <T> ApiResult<T> failed(IErrorCode errorCode) {
        return restResult(null, errorCode);
    }

    public static <T> ApiResult<T> restResult(T data, IErrorCode errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg());
    }

    private static <T> ApiResult<T> restResult(T data, long code, String msg) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        if (code == BaseErrorCode.SUCCESS.getCode()) {
            return apiResult;
        } else {
            throw new ApiResultException(apiResult);
        }
    }

    public boolean isOk() {
        return BaseErrorCode.SUCCESS.getCode() == code;
    }

    /**
     * 对于feign方式的调用 如果请求发生异常 则通过FeignException异常进行错误传递
     */
    @JSONField(serialize=false)
    public  T getFeignData() {
        if (!isOk()) {
            throw new FeignException(this);
        }
        return data;
    }

    /**
     * 服务间调用非业务正常，异常直接释放
     */
    @JSONField(serialize=false)
    public T serviceData() {
        if (!isOk()) {
            throw new GlobalException(this.msg);
        }
        return data;
    }

    /**
     * 服务间调用失败
     */
    public static <T> ApiResult<T> feignFail() {
        return failed(BaseErrorCode.FEIGN_FAIL);
    }

}