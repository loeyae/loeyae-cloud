package com.loeyae.cloud.task.service.impl;

import com.loeyae.cloud.task.entity.QrtzLocks;
import com.loeyae.cloud.task.mapper.QrtzLocksMapper;
import com.loeyae.cloud.task.service.IQrtzLocksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-17
 */
@Service
public class QrtzLocksServiceImpl extends ServiceImpl<QrtzLocksMapper, QrtzLocks> implements IQrtzLocksService {

}
