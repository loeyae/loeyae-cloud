package com.loeyae.cloud.message.provider;

import com.loeyae.cloud.message.entities.Message;

/**
 * IMessageProvider.
 *
 * @date: 2020-07-29
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
public interface IMessageProvider {

    public String send(Message message);
}