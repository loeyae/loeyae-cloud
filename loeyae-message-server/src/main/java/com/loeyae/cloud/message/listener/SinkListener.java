package com.loeyae.cloud.message.listener;

import com.alibaba.fastjson.JSONObject;
import com.loeyae.cloud.commons.tool.QueryWrapperUtils;
import com.loeyae.cloud.message.common.MessageConst;
import com.loeyae.cloud.message.entity.Consumer;
import com.loeyae.cloud.message.service.IConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;
import java.util.Map;

/**
 * SinkListener.
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020-07-29
 */
@EnableBinding({Sink.class})
@Slf4j
public class SinkListener {

    @Autowired
    IConsumerService consumerService;

    @StreamListener(value = Sink.INPUT, condition = "headers['target']>0")
    public void listen(@Payload JSONObject messageBody,
                       @Header(MessageConst.HEADER_UUID) String uuid,
                       @Header(MessageConst.HEADER_FROM) String from,
                       @Header(MessageConst.HEADER_ACTION) String action,
                       @Header(MessageConst.HEADER_TARGET) Integer target,
                       @Headers Map headers) throws InterruptedException {
        Consumer consumer = new Consumer();
        consumer.setTarget(target);
        List<Consumer> consumers = consumerService.list(QueryWrapperUtils.queryToWrapper(consumer, Consumer.class));

        JSONObject message = new JSONObject();
        message.put(MessageConst.MESSAGE_HEADERS, headers);
        message.put(MessageConst.MESSAGE_BODY, messageBody);
        log.info("got message: "+ message.toJSONString());
        for (int i = 0; i < consumers.size(); i++) {
            Consumer current = consumers.get(i);
            consumerService.callback(current, from, action, message);
        }
    }

}