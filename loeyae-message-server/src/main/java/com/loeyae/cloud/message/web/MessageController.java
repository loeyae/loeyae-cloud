package com.loeyae.cloud.message.web;

import com.alibaba.fastjson.JSONObject;
import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.message.entities.Message;
import com.loeyae.cloud.message.entities.MessageBody;
import com.loeyae.cloud.message.feign.CallerNotify;
import com.loeyae.cloud.message.provider.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.UUID;

/**
 * MessageController.
 *
 * @date: 2020-07-29
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    IMessageProvider messageProvider;

    @Autowired
    CallerNotify callerNotify;

    @PostMapping("/send")
    public ApiResult send(@RequestBody MessageBody messageBody)
    {
        int i = 0;
        while ( i < 10) {
            Message message = new Message();
            message.setUuid(UUID.randomUUID().toString());
            message.setAction("insert");
            message.setMessageBody(messageBody);
            messageProvider.send(message);
            i++;
        }
        return ApiResult.ok();
    }

    @PostMapping("/notify")
    public ApiResult callback(@RequestParam("s") String service, @RequestParam("f") String from,
                              @RequestParam("a") String action, @RequestBody JSONObject message) throws URISyntaxException {
        return callerNotify.notify(service, from, action, message);
    }
}