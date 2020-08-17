package com.loeyae.cloud.task.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.task.DTO.QrtzSchedulerStateCreate;
import com.loeyae.cloud.task.DTO.QrtzSchedulerStateQuery;
import com.loeyae.cloud.task.DTO.QrtzSchedulerStateUpdate;
import com.loeyae.cloud.task.VO.QrtzSchedulerStateView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  接口定义
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-17
 */
public interface QrtzSchedulerStateApi {

    @PostMapping("/")
    ApiResult<QrtzSchedulerStateView> create(@RequestBody QrtzSchedulerStateCreate data);

    @PutMapping("/{id}")
    ApiResult<QrtzSchedulerStateView> update(@PathVariable("id") int id, @RequestBody QrtzSchedulerStateUpdate data);

    @GetMapping("/{id}")
    ApiResult<QrtzSchedulerStateView> get(@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/")
    ApiResult<QrtzSchedulerStateView> one(QrtzSchedulerStateQuery data);

    @GetMapping("/all/")
    ApiResult<List<QrtzSchedulerStateView>> all(QrtzSchedulerStateQuery data);

    @GetMapping("/page/")
    ApiResult<PageResult<QrtzSchedulerStateView>> page(QrtzSchedulerStateQuery data);
}
