package com.loeyae.cloud.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.loeyae.cloud.commons.common.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * DemoController.
 *
 * @date: 2020-08-19
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@RestController
@Slf4j
public class DemoController {

    /**
     * 事件驱动Demo
     *
     * @param from
     * @param action
     * @param message
     * @return
     */
    @PostMapping("/callback/notify")
    public ApiResult<Boolean> receiveNotify(@RequestParam("from") String from, @RequestParam("action") String action, @RequestBody JSONObject message)
    {
        log.error("Got message: {} from: {} by: {}", message, from, action);
        return ApiResult.ok(true);
    }

    /**
     * 任务调度Demo
     *
     * @return
     */
    @GetMapping("/schedule/30/seconds")
    public ApiResult<Boolean> Per30Seconds()
    {
        log.error("Schedule every 30 seconds, run at: {}", new Date());
        return ApiResult.ok(true);
    }
}
