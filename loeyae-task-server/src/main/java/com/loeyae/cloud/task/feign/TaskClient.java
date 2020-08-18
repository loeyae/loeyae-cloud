package com.loeyae.cloud.task.feign;

import com.alibaba.fastjson.JSONObject;
import com.loeyae.cloud.commons.common.ApiResult;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * NotifyClient.
 *
 * @date: 2020-08-02
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
public interface TaskClient {

    @RequestLine("GET {url}")
    ApiResult requestGet(@Param("url") String url);

    @RequestLine("POST {url}")
    ApiResult requestPost(@Param("url") String url, @RequestBody JSONObject notifyDto);


}