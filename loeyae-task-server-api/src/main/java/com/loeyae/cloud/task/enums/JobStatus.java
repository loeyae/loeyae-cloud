package com.loeyae.cloud.task.enums;

/**
 * JobStatus.
 *
 * @date: 2020-08-18
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
public enum JobStatus {

    INIT(0, "初始化"),
    ACTIVE(1, "激活"),
    PAUSED(2, "激活"),
    CANCEL(3, "取消")
    ;

    private final int code;

    private final String msg;

    JobStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
