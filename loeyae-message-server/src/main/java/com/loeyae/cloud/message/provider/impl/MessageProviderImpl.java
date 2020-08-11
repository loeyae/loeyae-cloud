package com.loeyae.cloud.message.provider.impl;

import com.loeyae.cloud.message.common.MessageConst;
import com.loeyae.cloud.message.entity.Message;
import com.loeyae.cloud.message.provider.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * MessageProviderImpl.
 *
 * @date: 2020-07-29
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel output;

    @Override
    public String send(Message message) {
        org.springframework.messaging.Message msg =
                MessageBuilder.withPayload(message.getBody())
                        .setHeader(MessageConst.HEADER_UUID, message.getUuid())
                        .setHeader(MessageConst.HEADER_FROM, message.getFrom())
                        .setHeader(MessageConst.HEADER_ACTION, message.getAction())
                        .setHeader(MessageConst.HEADER_TARGET, message.getTarget())
                        .build();
        output.send(msg);
        return message.getUuid();
    }
}