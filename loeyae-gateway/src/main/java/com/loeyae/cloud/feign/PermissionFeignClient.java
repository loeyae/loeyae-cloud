package com.loeyae.cloud.feign;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.exception.FeignError;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * PermissionFeignClient.
 *
 * @date: 2020-08-10
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@FeignClient(name = "${service.oauth.name}", url = "${service.oauth.url}",
        fallbackFactory = PermissionFallbackFactory.class)
public interface PermissionFeignClient {

    @GetMapping("${service.oauth.api.menu}")
    ApiResult<List<Menu>> getMenuList(String userId);

    @GetMapping("${service.oauth.api.permission}")
    ApiResult<List<Permission>> getPermissionList(String userId);
}

@Configuration
class PermissionFallbackFactory implements FallbackFactory<PermissionFeignClientFailed>
{

    @Override
    public PermissionFeignClientFailed create(Throwable throwable) {
        FeignError.globalFail(throwable);
        return new PermissionFeignClientFailed();
    }
}

class PermissionFeignClientFailed implements PermissionFeignClient {

    @Override
    public ApiResult getMenuList(String userId) {
        return ApiResult.feignFail();
    }

    @Override
    public ApiResult<List<Permission>> getPermissionList(String userId) {
        return ApiResult.feignFail();
    }
}