package com.loeyae.cloud.task.service;

import com.loeyae.cloud.task.entity.ActualityJob;
import com.baomidou.mybatisplus.extension.service.IService;
import org.quartz.SchedulerException;

/**
 * <p>
 * 真实任务 服务类
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-18
 */
public interface IActualityJobService extends IService<ActualityJob> {

    /***
     * 创建任务
     *
     * @param entity
     * @return
     * @throws SchedulerException
     */
    ActualityJob create(ActualityJob entity) throws SchedulerException;

    /**
     * 更新任务信息
     *
     * @param id
     * @param entity
     * @return
     * @throws SchedulerException
     */
    ActualityJob updateJob(Integer id, ActualityJob entity) throws SchedulerException;

    /**
     * 暂停任务
     *
     * @param id
     * @throws SchedulerException
     */
    void pause(int id) throws SchedulerException;

    /**
     * 继续任务
     *
     * @param id
     * @throws SchedulerException
     */
    void resume(int id) throws SchedulerException;

    /**
     * 删除任务
     *
     * @param id
     * @throws SchedulerException
     */
    void delete(int id) throws SchedulerException;

    /**
     * 根据jobName获取任务
     *
     * @param jobName
     * @return
     */
    ActualityJob getByJobName(String jobName);
}
