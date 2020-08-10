package com.loeyae.cloud.feign;

import com.loeyae.cloud.commons.common.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * PermissionFeignClient.
 *
 * @date: 2020-08-10
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@FeignClient(name = "${service.oauth}")
public interface PermissionFeignClient {

    @GetMapping("")
    ApiResult getMenuList();
}