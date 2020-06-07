package com.loeyae.cloud.files.exception;

import com.loeyae.cloud.commons.exception.IErrorCode;

/**
 * FileErrorCode
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/6 18:11
 */
public enum  FileErrorCode implements IErrorCode {

    /**
     * 文件超出限制
     */
    MAX_FILE_ERROR(701, "文件大小超出限制"),
    ;

    private final long code;
    private final String msg;

    FileErrorCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }


    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", code, msg);
    }
}
