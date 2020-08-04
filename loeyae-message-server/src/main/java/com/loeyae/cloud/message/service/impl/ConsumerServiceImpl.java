package com.loeyae.cloud.message.service.impl;

import com.loeyae.cloud.message.entity.Consumer;
import com.loeyae.cloud.message.mapper.ConsumerMapper;
import com.loeyae.cloud.message.service.IConsumerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消费者 服务实现类
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-04
 */
@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements IConsumerService {

}
