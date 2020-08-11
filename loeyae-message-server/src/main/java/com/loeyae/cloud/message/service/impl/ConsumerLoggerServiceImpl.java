package com.loeyae.cloud.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loeyae.cloud.message.entity.ConsumerLogger;
import com.loeyae.cloud.message.mapper.ConsumerLoggerMapper;
import com.loeyae.cloud.message.service.IConsumerLoggerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消费者日志 服务实现类
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-06
 */
@Service
public class ConsumerLoggerServiceImpl extends ServiceImpl<ConsumerLoggerMapper, ConsumerLogger> implements IConsumerLoggerService {

}
