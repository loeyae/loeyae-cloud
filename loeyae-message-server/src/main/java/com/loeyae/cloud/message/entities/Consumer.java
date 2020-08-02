package com.loeyae.cloud.message.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * Consumer.
 *
 * @date: 2020-07-30
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Data
public class Consumer implements Serializable {

    private static final long serialVersionUID = 2175406604948897127L;
    private String service;
    private String action;
}