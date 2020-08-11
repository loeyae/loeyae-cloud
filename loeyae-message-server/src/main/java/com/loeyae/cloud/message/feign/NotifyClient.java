package com.loeyae.cloud.message.feign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loeyae.cloud.commons.common.ApiResult;
import feign.RequestLine;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

/**
 * NotifyClient.
 *
 * @date: 2020-08-02
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
public interface NotifyClient {

    @PostMapping("/callback/notify")
    ApiResult notify(@RequestParam("from") String from, @RequestParam("action") String action,
                     @RequestBody JSONObject notifyDto);


}