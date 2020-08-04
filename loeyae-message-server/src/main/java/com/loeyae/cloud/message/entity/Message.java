package com.loeyae.cloud.message.entity;

import com.alibaba.fastjson.JSONObject;
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
    private Integer target;
    private String from;
    private String to;
    private String action;

    private JSONObject body;
}