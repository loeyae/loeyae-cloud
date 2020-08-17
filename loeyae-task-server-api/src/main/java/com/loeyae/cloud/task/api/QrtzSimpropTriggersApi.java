package com.loeyae.cloud.task.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.task.DTO.QrtzSimpropTriggersCreate;
import com.loeyae.cloud.task.DTO.QrtzSimpropTriggersQuery;
import com.loeyae.cloud.task.DTO.QrtzSimpropTriggersUpdate;
import com.loeyae.cloud.task.VO.QrtzSimpropTriggersView;
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
public interface QrtzSimpropTriggersApi {

    @PostMapping("/")
    ApiResult<QrtzSimpropTriggersView> create(@RequestBody QrtzSimpropTriggersCreate data);

    @PutMapping("/{id}")
    ApiResult<QrtzSimpropTriggersView> update(@PathVariable("id") int id, @RequestBody QrtzSimpropTriggersUpdate data);

    @GetMapping("/{id}")
    ApiResult<QrtzSimpropTriggersView> get(@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/")
    ApiResult<QrtzSimpropTriggersView> one(QrtzSimpropTriggersQuery data);

    @GetMapping("/all/")
    ApiResult<List<QrtzSimpropTriggersView>> all(QrtzSimpropTriggersQuery data);

    @GetMapping("/page/")
    ApiResult<PageResult<QrtzSimpropTriggersView>> page(QrtzSimpropTriggersQuery data);
}
