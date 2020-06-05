package com.loeyae.cloud.exception;

/**
 * IErrorCode
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/5 15:37
 */
public interface IErrorCode {

    /**
     * 错误编码 -1、失败 0、成功
     */
    long getCode();

    /**
     * 错误描述
     */
    String getMsg();

    /**
     * 数据
     */
    Object getData();
}
