package com.loeyae.cloud.files.exception;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.exception.GlobalExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * ExceptionHandler
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/6 18:35
 */
public class ExceptionHandler extends GlobalExceptionHandler {

    @Override
    public ApiResult customEnd(Exception e, ApiResult r) {

        if (e instanceof MaxUploadSizeExceededException) {
            String msg = r.getMsg();
            r = new ApiResult(FileErrorCode.MAX_FILE_ERROR);
            r.setMsg(FileErrorCode.MAX_FILE_ERROR.getMsg() + ":" + msg);
            return r;
        }

        return super.customEnd(e, r);
    }
}