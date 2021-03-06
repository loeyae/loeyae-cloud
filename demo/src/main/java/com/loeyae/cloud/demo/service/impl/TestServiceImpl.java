package com.loeyae.cloud.demo.service.impl;

import com.loeyae.cloud.demo.entity.Test;
import com.loeyae.cloud.demo.mapper.TestMapper;
import com.loeyae.cloud.demo.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-06-30
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
