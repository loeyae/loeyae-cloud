package com.loeyae.cloud.task.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.task.DTO.*;
import com.loeyae.cloud.task.VO.ActualityJobView;
import com.loeyae.cloud.task.VO.JobExecuteLogView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 真实任务 接口定义
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-18
 */
public interface JobApi {

    @PostMapping("/")
    ApiResult<ActualityJobView> create(@RequestBody ActualityJobCreate data);

    @PutMapping("/{id}")
    ApiResult<ActualityJobView> update(@PathVariable("id") int id, @RequestBody ActualityJobUpdate data);

    @PutMapping("/{id}/pause/")
    ApiResult<Boolean> pause(@PathVariable("id") int id);

    @PutMapping("/{id}/resume/")
    ApiResult<Boolean> resume(@PathVariable("id") int id);

    @GetMapping("/{id}")
    ApiResult<ActualityJobView> get(@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/")
    ApiResult<ActualityJobView> one(ActualityJobQuery data);

    @GetMapping("/all/")
    ApiResult<List<ActualityJobView>> all(ActualityJobQuery data);

    @GetMapping("/page/")
    ApiResult<PageResult<ActualityJobView>> page(ActualityJobQuery data);

    @GetMapping("/log/{id}")
    ApiResult<PageResult<JobExecuteLogView>> log(@PathVariable("id") int id, JobExecuteLogQuery data);

}
