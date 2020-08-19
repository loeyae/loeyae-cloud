package com.loeyae.cloud.task.service.impl;

import com.loeyae.cloud.task.entity.JobExecuteLog;
import com.loeyae.cloud.task.mapper.JobExecuteLogMapper;
import com.loeyae.cloud.task.service.IJobExecuteLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务执行log 服务实现类
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-18
 */
@Service
public class JobExecuteLogServiceImpl extends ServiceImpl<JobExecuteLogMapper, JobExecuteLog> implements IJobExecuteLogService {

}
