package com.loeyae.cloud.message.service.impl;

import com.loeyae.cloud.message.entity.Producer;
import com.loeyae.cloud.message.mapper.ProducerMapper;
import com.loeyae.cloud.message.service.IProducerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 生产者 服务实现类
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-04
 */
@Service
public class ProducerServiceImpl extends ServiceImpl<ProducerMapper, Producer> implements IProducerService {

}
