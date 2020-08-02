package com.loeyae.cloud.message.entities;

import lombok.Data;

/**
 * Message Entity.
 *
 * @date: 2020-07-29
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Data
public class Message {
    private String uuid;
    private String action;

    private MessageBody messageBody;
}