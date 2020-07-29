package com.loeyae.cloud.message.web;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.message.entities.Message;
import com.loeyae.cloud.message.entities.MessageBody;
import com.loeyae.cloud.message.provider.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * MessageController.
 *
 * @date: 2020-07-29
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@RestController
@RequestMapping("/file")
public class MessageController {

    @Autowired
    IMessageProvider messageProvider;

    public ApiResult send(MessageBody messageBody)
    {
        Message message = new Message();
        message.setUuid(UUID.randomUUID().toString());
        message.setMessageBody(messageBody);
        messageProvider.send(message);
        return ApiResult.ok();
    }
}