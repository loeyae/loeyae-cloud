package com.loeyae.cloud.commons.exception;

/**
 * BaseErrorCode
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/5 15:36
 */
public enum BaseErrorCode implements IErrorCode {
    /**
     * 失败
     */
    FAILED(-1, "失败"),

    ERROR(-2, "缺少参数"),

    /**
     * 成功
     */
    NORMAL(0, "成功"),


    SUCCESS(200, "成功"),

    /**
     *  请持有有效令牌
     */
    TOKEN_INVALID(401, "令牌无效"),
    /**
     *  token超时
     */
    TOKEN_EXPIRE(409, "令牌过期"),

    /**
     * feign服务调用失败
     */
    FEIGN_FAIL(509, "服务调用失败"),
    ;

    private final long code;
    private final String msg;

    BaseErrorCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * fromCode
     *
     * @param code
     * @return
     */
    public static BaseErrorCode fromCode(long code) {
        BaseErrorCode[] ecs = BaseErrorCode.values();
        for (BaseErrorCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return NORMAL;
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
}
