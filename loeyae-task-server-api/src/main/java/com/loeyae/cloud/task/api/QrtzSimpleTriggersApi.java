package com.loeyae.cloud.task.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.task.DTO.QrtzSimpleTriggersCreate;
import com.loeyae.cloud.task.DTO.QrtzSimpleTriggersQuery;
import com.loeyae.cloud.task.DTO.QrtzSimpleTriggersUpdate;
import com.loeyae.cloud.task.VO.QrtzSimpleTriggersView;
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
public interface QrtzSimpleTriggersApi {

    @PostMapping("/")
    ApiResult<QrtzSimpleTriggersView> create(@RequestBody QrtzSimpleTriggersCreate data);

    @PutMapping("/{id}")
    ApiResult<QrtzSimpleTriggersView> update(@PathVariable("id") int id, @RequestBody QrtzSimpleTriggersUpdate data);

    @GetMapping("/{id}")
    ApiResult<QrtzSimpleTriggersView> get(@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/")
    ApiResult<QrtzSimpleTriggersView> one(QrtzSimpleTriggersQuery data);

    @GetMapping("/all/")
    ApiResult<List<QrtzSimpleTriggersView>> all(QrtzSimpleTriggersQuery data);

    @GetMapping("/page/")
    ApiResult<PageResult<QrtzSimpleTriggersView>> page(QrtzSimpleTriggersQuery data);
}
