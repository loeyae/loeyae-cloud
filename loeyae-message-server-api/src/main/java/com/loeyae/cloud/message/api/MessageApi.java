package com.loeyae.cloud.message.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.message.DTO.*;
import com.loeyae.cloud.message.VO.ConsumerView;
import com.loeyae.cloud.message.VO.ProducerView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 生产者 接口定义
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-04
 */
public interface MessageApi {

    @PostMapping("/notify/")
    ApiResult<Boolean> create(@RequestHeader("service") String service,
                              @RequestHeader("action") String action,
                              @RequestBody MessageBodyCreate messageBody);

    @PostMapping("/register/producer/")
    ApiResult<ProducerView> registerProducer(@RequestBody ProducerCreate producer);

    @PostMapping("/register/consumer/")
    ApiResult<ConsumerView> registerConsumer(@RequestBody ConsumerCreate consumer);

    @GetMapping("/producer/")
    ApiResult<ProducerView> getProducer(@RequestParam("id") int id);

    @GetMapping("/consumer/")
    ApiResult<ConsumerView> getConsumer(@RequestParam("id") int id);

    @DeleteMapping("/producer/{id}")
    ApiResult<Integer> deleteProducer(@PathVariable("id") int id);

    @DeleteMapping("/consumer/{id}")
    ApiResult<Integer> deleteConsumer(@PathVariable("id") int id);

    @GetMapping("/producer/")
    ApiResult<ProducerView> oneProducer(ProducerQuery data);

    @GetMapping("/consumer/")
    ApiResult<ConsumerView> oneConsumer(ConsumerQuery data);

    @GetMapping("/producer/all/")
    ApiResult<List<ProducerView>> getProducerList(ProducerQuery data);

    @GetMapping("/consumer/all/")
    ApiResult<List<ProducerView>> getConsumerList(ProducerQuery data);

}
