package com.loeyae.cloud.message.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.message.entity.Consumer;
import com.loeyae.cloud.message.entity.ConsumerLogger;
import com.loeyae.cloud.message.feign.CallerNotify;
import com.loeyae.cloud.message.mapper.ConsumerMapper;
import com.loeyae.cloud.message.service.IConsumerLoggerService;
import com.loeyae.cloud.message.service.IConsumerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

/**
 * <p>
 * 消费者 服务实现类
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-04
 */
@Service
@Slf4j
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements IConsumerService {

    @Autowired
    CallerNotify callerNotify;

    @Autowired
    IConsumerLoggerService consumerLoggerService;

    @Async("asyncTaskExecutor")
    @Override
    public void callback(Consumer consumer, String from, String action, JSONObject dto)
    {
        ConsumerLogger consumerLogger = new ConsumerLogger();
        consumerLogger.setConsumer(consumer.getId());
        consumerLogger.setService(consumer.getService());
        consumerLogger.setProducer(from);
        consumerLogger.setAction(action);
        consumerLogger.setMessage(dto);
        try {
            ApiResult<Boolean> r = callerNotify.notify(consumer.getService(), from, action, dto);
            if (r.isOk() && Boolean.TRUE.equals(r.getData())) {
                log.info("线程: "+ Thread.currentThread().getId() +" 调用成功");
                consumerLogger.setStatus(1);
            } else {
                consumerLogger.setStatus(2);
                log.info("线程: "+ Thread.currentThread().getId() +" 调用失败");
            }
        } catch (URISyntaxException e) {
            consumerLogger.setStatus(3);
            e.printStackTrace();
        }
        consumerLoggerService.save(consumerLogger);
    }
}
