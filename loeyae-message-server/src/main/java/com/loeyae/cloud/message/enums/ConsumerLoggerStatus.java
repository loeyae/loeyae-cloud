package com.loeyae.cloud.message.enums;

/**
 * ConsumerLoggerStatus.
 *
 * @date: 2020-08-06
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
public enum ConsumerLoggerStatus {

    SUCCESS(1, "success"),
    ;

    private final int code;
    private final String data;

    ConsumerLoggerStatus(int code, String data)
    {
        this.code = code;
        this.data = data;
    }
}
