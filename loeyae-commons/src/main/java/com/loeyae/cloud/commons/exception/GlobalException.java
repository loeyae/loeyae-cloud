package com.loeyae.cloud.commons.exception;

/**
 * GlobalException
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/5 15:43
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = -9048304393647572713L;

    /**
     * 错误码
     */
    private IErrorCode errorCode;

    public GlobalException(IErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(Throwable cause) {
        super(cause);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }

}
