package com.loeyae.cloud.commons.exception;

import org.springframework.validation.annotation.Validated;

/**
 * Validate Exception
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/29 10:15
 */
public class ValidateException extends GlobalException {
    private static final long serialVersionUID = 2240678250011531902L;

    private String validateMessage;

    public ValidateException(IErrorCode errorCode) {
        super(errorCode);
        this.validateMessage = errorCode.getMsg();
    }

    public ValidateException(String message) {
        super(BaseErrorCode.VALIDATE_FAIL.getMsg());
        this.validateMessage = message;
    }

    public String getValidateMessage() {
        return validateMessage;
    }
}
