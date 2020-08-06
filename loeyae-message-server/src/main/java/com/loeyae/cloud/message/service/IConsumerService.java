package com.loeyae.cloud.message.service;

import com.alibaba.fastjson.JSONObject;
import com.loeyae.cloud.message.entity.Consumer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 消费者 服务类
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-04
 */
public interface IConsumerService extends IService<Consumer> {

    public void callback(Consumer consumer, String from, String action, JSONObject dto);
}
