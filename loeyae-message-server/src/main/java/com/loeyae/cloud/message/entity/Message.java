package com.loeyae.cloud.message.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Message Entity.
 *
 * @date: 2020-07-29
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = -7086512639378927485L;
    private String uuid;
    private String from;
    private String action;

    private MessageBody body;
}