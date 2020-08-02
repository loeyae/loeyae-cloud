package com.loeyae.cloud.message.listener;

import com.loeyae.cloud.message.common.MessageConst;
import com.loeyae.cloud.message.entities.MessageBody;
import com.loeyae.cloud.message.processor.Default;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

/**
 * DefaultListener.
 *
 * @date: 2020-08-02
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@EnableBinding(Default.class)
@Slf4j
public class DefaultListener {

    @StreamListener(Default.INPUT)
    public void listen(@Payload MessageBody messageBody,
                       @Header(MessageConst.HEADER_ACTION) String action,
                       @Header(MessageConst.HEADER_UUID) String uuid,
                       @Headers Map headers) throws InterruptedException {
        log.warn("default got message : uuid "+ uuid +" body "+ messageBody.toString());
        Thread.sleep(100);
    }
}