package com.loeyae.cloud.task.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.task.DTO.QrtzTriggersCreate;
import com.loeyae.cloud.task.DTO.QrtzTriggersQuery;
import com.loeyae.cloud.task.DTO.QrtzTriggersUpdate;
import com.loeyae.cloud.task.VO.QrtzTriggersView;
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
public interface QrtzTriggersApi {

    @PostMapping("/")
    ApiResult<QrtzTriggersView> create(@RequestBody QrtzTriggersCreate data);

    @PutMapping("/{id}")
    ApiResult<QrtzTriggersView> update(@PathVariable("id") int id, @RequestBody QrtzTriggersUpdate data);

    @GetMapping("/{id}")
    ApiResult<QrtzTriggersView> get(@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/")
    ApiResult<QrtzTriggersView> one(QrtzTriggersQuery data);

    @GetMapping("/all/")
    ApiResult<List<QrtzTriggersView>> all(QrtzTriggersQuery data);

    @GetMapping("/page/")
    ApiResult<PageResult<QrtzTriggersView>> page(QrtzTriggersQuery data);
}
