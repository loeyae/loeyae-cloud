package com.loeyae.cloud.message.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.SubscribableChannel;

/**
 * Default Processor.
 *
 * @date: 2020-08-02
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
public interface Default extends Source {

    String INPUT = "default";

    @Input(INPUT)
    SubscribableChannel input();
}