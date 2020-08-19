package com.loeyae.cloud.task.quartz;

import com.alibaba.fastjson.JSON;
import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.tool.BeanUtils;
import com.loeyae.cloud.task.entity.ActualityJob;
import com.loeyae.cloud.task.entity.JobExecuteLog;
import com.loeyae.cloud.task.feign.CallerTask;
import com.loeyae.cloud.task.service.IActualityJobService;
import com.loeyae.cloud.task.service.IJobExecuteLogService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * FeignCallerJobBean
 *
 * @date: 2020-08-18
 * @version: 1.0
 * @author zhangyi07@beyondsoft.com
 */
@Slf4j
public class FeignCallerJobBean extends QuartzJobBean {

    @Autowired
    private IActualityJobService actualityJobService;

    @Autowired
    private IJobExecuteLogService jobExecuteLogService;

    @Autowired
    CallerTask callerTask;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        String name = jobExecutionContext.getJobDetail().getKey().getName();
        ActualityJob actualityJob = actualityJobService.getByJobName(name);
        if (ObjectUtils.isNotEmpty(actualityJob) && ObjectUtils.isNotEmpty(actualityJob.getService()) && ObjectUtils.isNotEmpty(actualityJob.getUrl())) {
            int retries = 0;
            Long original = 0L;
            JobExecuteLog sourceLog = new JobExecuteLog();
            while (retries < 3) {
                JobExecuteLog jobExecuteLog = new JobExecuteLog();
                jobExecuteLog.setJobId(actualityJob.getId());
                jobExecuteLog.setOriginal(original);
                jobExecuteLog.setRetries(retries);
                try {
                    ApiResult result;
                    if (actualityJob.getMethod().equalsIgnoreCase("post")) {
                        result = callerTask.post(actualityJob.getService(), actualityJob.getUrl(), actualityJob.getParameter());
                    } else {
                        result = callerTask.get(actualityJob.getService(), actualityJob.getUrl());
                    }
                    if (ObjectUtils.isNotEmpty(result)) {
                        if (result.isOk()) {
                            jobExecuteLog.setSuccess(1);
                        } else {
                            jobExecuteLog.setSuccess(0);
                        }
                    }
                    jobExecuteLog.setResult(JSON.toJSONString(result));
                } catch (Exception e) {
                    log.error("Feign Call Error", e);
                    jobExecuteLog.setSuccess(-1);
                    jobExecuteLog.setResult(e.getMessage());
                }
                jobExecuteLogService.save(jobExecuteLog);
                if (jobExecuteLog.getSuccess() == 1) {
                    break;
                }
                if (original == 0) {
                    sourceLog = BeanUtils.copyToEntity(jobExecuteLog, JobExecuteLog.class);
                    original = jobExecuteLog.getId();
                } else {
                    sourceLog.setRetries(retries);
                    sourceLog.setSuccess(jobExecuteLog.getSuccess());
                    jobExecuteLogService.updateById(sourceLog);
                }
                retries++;
            }
        }
    }

}
