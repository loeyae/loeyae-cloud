package com.loeyae.cloud.message.web;

import com.alibaba.fastjson.JSONObject;
import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.tool.BeanUtils;
import com.loeyae.cloud.commons.tool.QueryWapperUtils;
import com.loeyae.cloud.commons.tool.ValidateUtil;
import com.loeyae.cloud.message.DTO.*;
import com.loeyae.cloud.message.VO.ConsumerView;
import com.loeyae.cloud.message.VO.ProducerView;
import com.loeyae.cloud.message.api.MessageApi;
import com.loeyae.cloud.message.entity.Consumer;
import com.loeyae.cloud.message.entity.Message;
import com.loeyae.cloud.message.entity.Producer;
import com.loeyae.cloud.message.entity.ProducerLogger;
import com.loeyae.cloud.message.feign.CallerNotify;
import com.loeyae.cloud.message.provider.IMessageProvider;
import com.loeyae.cloud.message.service.IConsumerService;
import com.loeyae.cloud.message.service.IProducerLoggerService;
import com.loeyae.cloud.message.service.IProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * MessageController.
 *
 * @date: 2020-07-29
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController implements MessageApi {

    @Autowired
    IMessageProvider messageProvider;

    @Autowired
    IProducerService producerService;

    @Autowired
    IConsumerService consumerService;

    @Autowired
    IProducerLoggerService producerLoggerService;

    @Autowired
    CallerNotify callerNotify;

    @Override
    public ApiResult<Boolean> create(@RequestHeader("service") String service,
                            @RequestHeader("action") String action,
                            @RequestBody JSONObject messageBody)
    {
        Message message = new Message();
        message.setUuid(UUID.randomUUID().toString());
        message.setFrom(service);
        message.setAction(action);
        message.setBody(messageBody);
        Producer producer = new Producer();
        producer.setAction(action);
        producer.setService(service);
        Producer r = producerService.getOne(QueryWapperUtils.queryToWrapper(producer, Producer.class));
        log.info(r.toString());
        ProducerLogger producerLogger = new ProducerLogger();
        producerLogger.setService(service);
        producerLogger.setAction(action);
        producerLogger.setMessage(messageBody);
        if (Objects.isNull(r)) {
            message.setTarget(0);
            producerLogger.setProducer(0);
        } else {
            message.setTarget(r.getId());
            producerLogger.setProducer(r.getId());
        }
        producerLoggerService.save(producerLogger);
        log.info("send message: "+ message.toString());
        messageProvider.send(message);
        return ApiResult.ok(true);
    }

    @Override
    public ApiResult<ProducerView> registerProducer(ProducerCreate producer) {
        ValidateUtil.validateEntity(producer);
        Producer producerEntity = BeanUtils.copyToEntity(producer, Producer.class);
        producerService.save(producerEntity);
        return ApiResult.ok(BeanUtils.copyToEntity(producerEntity, ProducerView.class));
    }

    @Override
    public ApiResult<ConsumerView> registerConsumer(ConsumerCreate consumer) {
        ValidateUtil.validateEntity(consumer);
        Consumer consumerEntity = BeanUtils.copyToEntity(consumer, Consumer.class);
        consumerService.save(consumerEntity);
        return ApiResult.ok(BeanUtils.copyToEntity(consumerEntity, ConsumerView.class));
    }

    @Override
    public ApiResult<ProducerView> getProducer(int id) {
        Producer producer = producerService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(producer, ProducerView.class));
    }

    @Override
    public ApiResult<ConsumerView> getConsumer(int id) {
        Consumer consumer = consumerService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(consumer, ConsumerView.class));
    }

    @Override
    public ApiResult<Integer> deleteProducer(int id) {
        Boolean r = producerService.removeById(id);
        return ApiResult.ok(r ? 1 : 0);
    }

    @Override
    public ApiResult<Integer> deleteConsumer(int id) {
        Boolean r = consumerService.removeById(id);
        return ApiResult.ok(r ? 1 : 0);
    }

    @Override
    public ApiResult<ProducerView> oneProducer(ProducerQuery data) {
        ValidateUtil.validateEntity(data);
        Producer producer = producerService.getOne(QueryWapperUtils.queryToWrapper(data, Producer.class));
        return ApiResult.ok(BeanUtils.copyToEntity(producer, ProducerView.class));
    }

    @Override
    public ApiResult<ConsumerView> oneConsumer(ConsumerQuery data) {
        ValidateUtil.validateEntity(data);
        Consumer consumer = consumerService.getOne(QueryWapperUtils.queryToWrapper(data, Consumer.class));
        return ApiResult.ok(BeanUtils.copyToEntity(consumer, ConsumerView.class));
    }

    @Override
    public ApiResult<List<ProducerView>> getProducerList(ProducerQuery data) {
        ValidateUtil.validateEntity(data);
        List<Producer> producers = producerService.list(QueryWapperUtils.queryToWrapper(data, Producer.class));
        return ApiResult.ok(BeanUtils.copyObjListProperties(producers, ProducerView.class));
    }

    @Override
    public ApiResult<List<ConsumerView>> getConsumerList(ConsumerQuery data) {
        ValidateUtil.validateEntity(data);
        List<Consumer> consumers = consumerService.list(QueryWapperUtils.queryToWrapper(data, Consumer.class));
        return ApiResult.ok(BeanUtils.copyObjListProperties(consumers, ConsumerView.class));
    }

    @PostMapping("/call")
    public ApiResult call(@RequestParam("s") String service, @RequestParam("f") String from,
                              @RequestParam("a") String action, @RequestBody JSONObject message) throws URISyntaxException {
        return callerNotify.notify(service, from, action, message);
    }
}