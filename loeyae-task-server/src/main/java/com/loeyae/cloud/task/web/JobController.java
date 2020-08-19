package com.loeyae.cloud.task.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.commons.exception.BaseErrorCode;
import com.loeyae.cloud.commons.tool.BeanUtils;
import com.loeyae.cloud.commons.tool.QueryWrapperUtils;
import com.loeyae.cloud.commons.tool.ValidateUtil;
import com.loeyae.cloud.task.DTO.ActualityJobCreate;
import com.loeyae.cloud.task.DTO.ActualityJobQuery;
import com.loeyae.cloud.task.DTO.ActualityJobUpdate;
import com.loeyae.cloud.task.DTO.JobExecuteLogQuery;
import com.loeyae.cloud.task.VO.ActualityJobView;
import com.loeyae.cloud.task.VO.JobExecuteLogView;
import com.loeyae.cloud.task.api.JobApi;
import com.loeyae.cloud.task.entity.ActualityJob;
import com.loeyae.cloud.task.entity.JobExecuteLog;
import com.loeyae.cloud.task.feign.CallerTask;
import com.loeyae.cloud.task.quartz.FeignCallerJobBean;
import com.loeyae.cloud.task.service.IActualityJobService;
import com.loeyae.cloud.task.service.IJobExecuteLogService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * JobController.
 *
 * @date: 2020-08-18
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@RestController
@RequestMapping("/job")
@Slf4j
public class JobController implements JobApi {

    @Autowired
    private IActualityJobService actualityJobService;

    @Autowired
    private IJobExecuteLogService jobExecuteLogService;

    /**
     * 注入任务调度器
     */
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private CallerTask callerTask;

    @GetMapping("/test")
    public ApiResult<Boolean> testJob(@RequestParam("id") String id)
    {
        //任务名称
        String name = id;
        //任务所属分组
        String group = FeignCallerJobBean.class.getName();

        JobKey jobKey = new JobKey(name, group);
        try {
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResult.ok(true);
    }

    @GetMapping("/feign")
    public ApiResult testFeign()
    {
        ApiResult result = callerTask.get("LOEYAE-DEMO", "/demo/test/6");
        return result;
    }

    @Override
    public ApiResult<ActualityJobView> create(ActualityJobCreate data) {
        ValidateUtil.validateEntity(data);
        try {
            ActualityJob actualityJob = actualityJobService.create(BeanUtils.copyToEntity(data, ActualityJob.class));
            return ApiResult.ok(BeanUtils.copyToEntity(actualityJob, ActualityJobView.class));
        } catch (Exception e) {
            log.error("Job Create Error", e);
            return ApiResult.failed(BaseErrorCode.FAILED);
        }
    }

    @Override
    public ApiResult<ActualityJobView> update(int id, ActualityJobUpdate data) {
        ValidateUtil.validateEntity(data);
        try {
            ActualityJob actualityJob = actualityJobService.updateJob(id, BeanUtils.copyToEntity(data, ActualityJob.class));
            return ApiResult.ok(BeanUtils.copyToEntity(actualityJob, ActualityJobView.class));
        } catch (Exception e) {
            log.error("Job Update Error", e);
            return ApiResult.failed(BaseErrorCode.FAILED);
        }
    }

    @Override
    public ApiResult<Boolean> pause(int id) {
        try {
            actualityJobService.pause(id);
            return ApiResult.ok(true);
        } catch (Exception e) {
            log.error("Job Pause Error", e);
            return ApiResult.failed(BaseErrorCode.FAILED);
        }
    }

    @Override
    public ApiResult<Boolean> resume(int id) {
        try {
            actualityJobService.resume(id);
            return ApiResult.ok(true);
        } catch (Exception e) {
            log.error("Job Resume Error", e);
            return ApiResult.failed(BaseErrorCode.FAILED);
        }
    }

    @Override
    public ApiResult<ActualityJobView> get(int id) {
        ActualityJob result = actualityJobService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(result, ActualityJobView.class));
    }

    @Override
    public ApiResult<Integer> delete(int id) {
        try {
            actualityJobService.delete(id);
            return ApiResult.ok(1);
        } catch (Exception e) {
            log.error("Job Delete Error", e);
            return ApiResult.failed(BaseErrorCode.FAILED);
        }
    }

    @Override
    public ApiResult<ActualityJobView> one(ActualityJobQuery data) {
        ValidateUtil.validateEntity(data);
        QueryWrapper queryWrapper = QueryWrapperUtils.queryToWrapper(data, ActualityJob.class);
        ActualityJob result = actualityJobService.getOne(queryWrapper);
        return ApiResult.ok(BeanUtils.copyToEntity(result, ActualityJobView.class));
    }

    @Override
    public ApiResult<List<ActualityJobView>> all(ActualityJobQuery data) {
        ValidateUtil.validateEntity(data);
        QueryWrapper queryWrapper = QueryWrapperUtils.queryToWrapper(data, ActualityJob.class);
        List<ActualityJob> result = actualityJobService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, ActualityJobView.class));
    }

    @Override
    public ApiResult<PageResult<ActualityJobView>> page(ActualityJobQuery data) {
        ValidateUtil.validateEntity(data);
        IPage<ActualityJob> page = QueryWrapperUtils.ofPage(data);
        QueryWrapper queryWrapper = QueryWrapperUtils.queryToWrapper(data, ActualityJob.class);
        IPage<ActualityJob> result = actualityJobService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(result, ActualityJobView.class));
    }

    @Override
    public ApiResult<PageResult<JobExecuteLogView>> log(int id, JobExecuteLogQuery data) {
        ValidateUtil.validateEntity(data);
        data.setJobId(id);
        IPage<JobExecuteLog> page = QueryWrapperUtils.ofPage(data);
        QueryWrapper queryWrapper = QueryWrapperUtils.queryToWrapper(data, JobExecuteLog.class);
        IPage<JobExecuteLog> result = jobExecuteLogService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(result, JobExecuteLogView.class));
    }
}
