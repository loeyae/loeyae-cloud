package com.loeyae.cloud.message.entities;

import lombok.Data;

/**
 * .
 *
 * @date: 2020-07-29
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Data
public class MessageBody {
    private String fromService;
    private String message;
}