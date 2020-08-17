package com.loeyae.cloud.task.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.task.DTO.QrtzCronTriggersCreate;
import com.loeyae.cloud.task.DTO.QrtzCronTriggersQuery;
import com.loeyae.cloud.task.DTO.QrtzCronTriggersUpdate;
import com.loeyae.cloud.task.VO.QrtzCronTriggersView;
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
public interface QrtzCronTriggersApi {

    @PostMapping("/")
    ApiResult<QrtzCronTriggersView> create(@RequestBody QrtzCronTriggersCreate data);

    @PutMapping("/{id}")
    ApiResult<QrtzCronTriggersView> update(@PathVariable("id") int id, @RequestBody QrtzCronTriggersUpdate data);

    @GetMapping("/{id}")
    ApiResult<QrtzCronTriggersView> get(@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/")
    ApiResult<QrtzCronTriggersView> one(QrtzCronTriggersQuery data);

    @GetMapping("/all/")
    ApiResult<List<QrtzCronTriggersView>> all(QrtzCronTriggersQuery data);

    @GetMapping("/page/")
    ApiResult<PageResult<QrtzCronTriggersView>> page(QrtzCronTriggersQuery data);
}
