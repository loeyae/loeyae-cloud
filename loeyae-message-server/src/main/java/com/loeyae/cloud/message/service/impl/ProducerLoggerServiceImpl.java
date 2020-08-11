package com.loeyae.cloud.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loeyae.cloud.message.entity.ProducerLogger;
import com.loeyae.cloud.message.mapper.ProducerLoggerMapper;
import com.loeyae.cloud.message.service.IProducerLoggerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 生产者日志 服务实现类
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-06
 */
@Service
public class ProducerLoggerServiceImpl extends ServiceImpl<ProducerLoggerMapper, ProducerLogger> implements IProducerLoggerService {

}
