package com.loeyae.cloud.commons.exception;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * FeignError
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/6 18:31
 */
@Slf4j
@ToString
@Data
public class FeignError {

    /**
     * 基本信息
     */
    private String base;

    /**
     * 详细信息
     */
    private String detail;

    /**
     * 原因
     */
    private String cause;

    /**
     * 详细原因
     */
    private String causeDetail;

    @Override
    public String toString() {
        return "FeignError{" +
                "错误信息='" + base + '\'' +
                ", 详情='" + detail + '\'' +
                ", 原因='" + cause + '\'' +
                ", 详细原因='" + causeDetail + '\'' +
                '}';
    }

    private static String error = "服务调用错误";

    public static void globalFail() {
        globalFail("");
    }

    public static void globalFail(String msg) {
        throw new GlobalException(error + ":" + msg);
    }

    public static void globalFail(Throwable throwable) {
        throwable.printStackTrace();;
        log.error(throwable.toString());
        globalFail("");
    }
}
