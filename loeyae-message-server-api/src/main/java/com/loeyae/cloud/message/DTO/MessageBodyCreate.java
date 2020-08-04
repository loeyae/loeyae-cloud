package com.loeyae.cloud.message.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * .
 *
 * @date: 2020-07-29
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Data
public class MessageBodyCreate implements Serializable {
    private static final long serialVersionUID = -4437322533444544011L;
    private String from;
    private String message;
}