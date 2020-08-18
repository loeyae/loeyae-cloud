package com.loeyae.cloud.task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loeyae.cloud.commons.tool.BeanUtils;
import com.loeyae.cloud.commons.tool.SpringContextTool;
import com.loeyae.cloud.task.entity.ActualityJob;
import com.loeyae.cloud.task.mapper.ActualityJobMapper;
import com.loeyae.cloud.task.quartz.FeignCallerJobBean;
import com.loeyae.cloud.task.service.IActualityJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * <p>
 * 真实任务 服务实现类
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-18
 */
@Service
public class ActualityJobServiceImpl extends ServiceImpl<ActualityJobMapper, ActualityJob> implements IActualityJobService {

    @Autowired
    private  Scheduler scheduler;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActualityJob create(ActualityJob entity) throws SchedulerException {
        //任务名称
        String name = UUID.randomUUID().toString();
        entity.setJobName(name);
        this.save(entity);

        //任务所属分组
        String group = entity.getService();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(entity.getCron());
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(FeignCallerJobBean.class).withIdentity(name, group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(scheduleBuilder).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
        return entity;
    }

    @Override
    public ActualityJob updateJob(Integer id, ActualityJob entity) throws SchedulerException {
        ActualityJob updateEntity = this.getById(id);
        ActualityJob source = BeanUtils.copyToEntity(updateEntity, ActualityJob.class);
        BeanUtils.copyProperties(entity, updateEntity);
        this.updateById(updateEntity);
        if (!source.getCron().equals(updateEntity.getCron())) {
            String name = source.getJobName();
            //任务所属分组
            String group = entity.getService();

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(entity.getCron());
            //创建任务触发器
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();
            TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
            //调整cron
            scheduler.rescheduleJob(triggerKey, trigger);
        }
        return updateEntity;
    }

    @Override
    public void pause(int id) throws SchedulerException {
        ActualityJob entity = this.getById(id);
        JobKey jobKey = JobKey.jobKey(entity.getJobName(), entity.getService());
        scheduler.pauseJob(jobKey);
    }

    @Override
    public void resume(int id) throws SchedulerException {
        ActualityJob entity = this.getById(id);
        JobKey jobKey = JobKey.jobKey(entity.getJobName(), entity.getService());
        scheduler.resumeJob(jobKey);
    }


    @Override
    @Transactional
    public void delete(int id) throws SchedulerException {
        ActualityJob entity = this.getById(id);
        JobKey jobKey = new JobKey(entity.getJobName(), entity.getService());
        scheduler.deleteJob(jobKey);
        this.removeById(id);
    }

    @Override
    public ActualityJob getByJobName(String jobName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        ActualityJob query = new ActualityJob();
        query.setJobName(jobName);
        queryWrapper.setEntity(query);
        return baseMapper.selectOne(queryWrapper);
    }

}
