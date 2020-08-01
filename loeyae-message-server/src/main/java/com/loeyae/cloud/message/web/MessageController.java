package com.loeyae.cloud.message.web;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.message.entities.Consumer;
import com.loeyae.cloud.message.entities.Message;
import com.loeyae.cloud.message.entities.MessageBody;
import com.loeyae.cloud.message.provider.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/notify/{action}")
    public ApiResult notify(@PathVariable("action") String action,
                          @RequestBody MessageBody messageBody)
    {
        Message message = new Message();
        message.setUuid(UUID.randomUUID().toString());
        message.setBody(messageBody);
        messageProvider.send(message);
        return ApiResult.ok();
    }

    @PostMapping("/register")
    public ApiResult register(Consumer consumer)
    {
        return ApiResult.ok();
    }



}