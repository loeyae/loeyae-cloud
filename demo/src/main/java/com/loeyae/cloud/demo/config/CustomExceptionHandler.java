package com.loeyae.cloud.demo.config;

import com.loeyae.cloud.commons.exception.GlobalExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * CustomExceptionHandler
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/28 17:05
 */
@RestControllerAdvice
public class CustomExceptionHandler extends GlobalExceptionHandler {
}
