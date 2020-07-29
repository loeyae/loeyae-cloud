package com.loeyae.cloud.message.listener;

import com.loeyae.cloud.message.common.MessageConst;
import com.loeyae.cloud.message.entities.MessageBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

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

    @StreamListener(Sink.INPUT)
    public void listen(@Payload MessageBody messageBody, @Header(MessageConst.HEADER_UUID) String uuid)
    {
        log.warn("got message : uuid "+ uuid +" body "+ messageBody.toString());
    }
}