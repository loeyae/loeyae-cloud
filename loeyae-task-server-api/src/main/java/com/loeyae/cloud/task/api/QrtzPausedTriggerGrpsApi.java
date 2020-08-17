package com.loeyae.cloud.task.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.task.DTO.QrtzPausedTriggerGrpsCreate;
import com.loeyae.cloud.task.DTO.QrtzPausedTriggerGrpsQuery;
import com.loeyae.cloud.task.DTO.QrtzPausedTriggerGrpsUpdate;
import com.loeyae.cloud.task.VO.QrtzPausedTriggerGrpsView;
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
public interface QrtzPausedTriggerGrpsApi {

    @PostMapping("/")
    ApiResult<QrtzPausedTriggerGrpsView> create(@RequestBody QrtzPausedTriggerGrpsCreate data);

    @PutMapping("/{id}")
    ApiResult<QrtzPausedTriggerGrpsView> update(@PathVariable("id") int id, @RequestBody QrtzPausedTriggerGrpsUpdate data);

    @GetMapping("/{id}")
    ApiResult<QrtzPausedTriggerGrpsView> get(@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/")
    ApiResult<QrtzPausedTriggerGrpsView> one(QrtzPausedTriggerGrpsQuery data);

    @GetMapping("/all/")
    ApiResult<List<QrtzPausedTriggerGrpsView>> all(QrtzPausedTriggerGrpsQuery data);

    @GetMapping("/page/")
    ApiResult<PageResult<QrtzPausedTriggerGrpsView>> page(QrtzPausedTriggerGrpsQuery data);
}
