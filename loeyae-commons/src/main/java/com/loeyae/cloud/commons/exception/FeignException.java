package com.loeyae.cloud.commons.exception;

import com.loeyae.cloud.commons.common.ApiResult;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * FeignException
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/6 18:26
 */
@Data
@AllArgsConstructor
public class FeignException extends RuntimeException {
    private static final long serialVersionUID = 2989815706468995965L;
    /**
     * 错误码
     */
    private ApiResult apiResult;
}
