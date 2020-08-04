package com.loeyae.cloud.message.web;

import com.alibaba.fastjson.JSONObject;
import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.message.DTO.*;
import com.loeyae.cloud.message.VO.ConsumerView;
import com.loeyae.cloud.message.VO.ProducerView;
import com.loeyae.cloud.message.api.MessageApi;
import com.loeyae.cloud.message.entity.Consumer;
import com.loeyae.cloud.message.entity.Message;
import com.loeyae.cloud.message.entity.MessageBody;
import com.loeyae.cloud.message.entity.Producer;
import com.loeyae.cloud.message.feign.CallerNotify;
import com.loeyae.cloud.message.provider.IMessageProvider;
import com.loeyae.cloud.message.service.IConsumerService;
import com.loeyae.cloud.message.service.IProducerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
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
public class MessageController implements MessageApi {

    @Autowired
    IMessageProvider messageProvider;

    @Autowired
    IProducerService producerService;

    @Autowired
    IConsumerService consumerService;

    @Autowired
    CallerNotify callerNotify;

    @Override
    public ApiResult<Boolean> create(@RequestHeader("service") String service,
                            @RequestHeader("action") String action,
                            @RequestBody MessageBodyCreate messageBodyCreate)
    {
        Message message = new Message();
        message.setUuid(UUID.randomUUID().toString());
        message.setFrom(service);
        message.setAction(action);
        MessageBody messageBody = new MessageBody();
        BeanUtils.copyProperties(messageBodyCreate, messageBody);
        message.setBody(messageBody);
        messageProvider.send(message);
        return ApiResult.ok(true);
    }

    @Override
    public ApiResult<ProducerView> registerProducer(@Validated ProducerCreate producer) {
        Producer producerEntity = new Producer();
        BeanUtils.copyProperties(producer, producerEntity);
        producerService.save(producerEntity);
        return null;
    }

    @Override
    public ApiResult<ConsumerView> registerConsumer(ConsumerCreate consumer) {
        return null;
    }

    @Override
    public ApiResult<ProducerView> getProducer(int id) {
        return null;
    }

    @Override
    public ApiResult<ConsumerView> getConsumer(int id) {
        return null;
    }

    @Override
    public ApiResult<Integer> deleteProducer(int id) {
        return null;
    }

    @Override
    public ApiResult<Integer> deleteConsumer(int id) {
        return null;
    }

    @Override
    public ApiResult<ProducerView> oneProducer(ProducerQuery data) {
        return null;
    }

    @Override
    public ApiResult<ConsumerView> oneConsumer(ConsumerQuery data) {
        return null;
    }

    @Override
    public ApiResult<List<ProducerView>> getProducerList(ProducerQuery data) {
        return null;
    }

    @Override
    public ApiResult<List<ProducerView>> getConsumerList(ProducerQuery data) {
        return null;
    }

    @PostMapping("/call")
    public ApiResult call(@RequestParam("s") String service, @RequestParam("f") String from,
                              @RequestParam("a") String action, @RequestBody JSONObject message) throws URISyntaxException {
        return callerNotify.notify(service, from, action, message);
    }
}