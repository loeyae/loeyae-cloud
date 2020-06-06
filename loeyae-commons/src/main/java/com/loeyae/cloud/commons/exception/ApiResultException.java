package com.loeyae.cloud.commons.exception;

import com.loeyae.cloud.commons.common.ApiResult;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ApiResultException
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/6 18:29
 */
@AllArgsConstructor
@Data
public class ApiResultException extends RuntimeException {
    private static final long serialVersionUID = 1432576326879712109L;

    private ApiResult apiResult;
}
