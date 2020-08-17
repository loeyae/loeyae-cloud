package com.loeyae.cloud.task.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.task.DTO.QrtzFiredTriggersCreate;
import com.loeyae.cloud.task.DTO.QrtzFiredTriggersQuery;
import com.loeyae.cloud.task.DTO.QrtzFiredTriggersUpdate;
import com.loeyae.cloud.task.VO.QrtzFiredTriggersView;
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
public interface QrtzFiredTriggersApi {

    @PostMapping("/")
    ApiResult<QrtzFiredTriggersView> create(@RequestBody QrtzFiredTriggersCreate data);

    @PutMapping("/{id}")
    ApiResult<QrtzFiredTriggersView> update(@PathVariable("id") int id, @RequestBody QrtzFiredTriggersUpdate data);

    @GetMapping("/{id}")
    ApiResult<QrtzFiredTriggersView> get(@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/")
    ApiResult<QrtzFiredTriggersView> one(QrtzFiredTriggersQuery data);

    @GetMapping("/all/")
    ApiResult<List<QrtzFiredTriggersView>> all(QrtzFiredTriggersQuery data);

    @GetMapping("/page/")
    ApiResult<PageResult<QrtzFiredTriggersView>> page(QrtzFiredTriggersQuery data);
}
