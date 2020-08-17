package com.loeyae.cloud.task.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.task.DTO.QrtzBlobTriggersCreate;
import com.loeyae.cloud.task.DTO.QrtzBlobTriggersQuery;
import com.loeyae.cloud.task.DTO.QrtzBlobTriggersUpdate;
import com.loeyae.cloud.task.VO.QrtzBlobTriggersView;
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
public interface QrtzBlobTriggersApi {

    @PostMapping("/")
    ApiResult<QrtzBlobTriggersView> create(@RequestBody QrtzBlobTriggersCreate data);

    @PutMapping("/{id}")
    ApiResult<QrtzBlobTriggersView> update(@PathVariable("id") int id, @RequestBody QrtzBlobTriggersUpdate data);

    @GetMapping("/{id}")
    ApiResult<QrtzBlobTriggersView> get(@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/")
    ApiResult<QrtzBlobTriggersView> one(QrtzBlobTriggersQuery data);

    @GetMapping("/all/")
    ApiResult<List<QrtzBlobTriggersView>> all(QrtzBlobTriggersQuery data);

    @GetMapping("/page/")
    ApiResult<PageResult<QrtzBlobTriggersView>> page(QrtzBlobTriggersQuery data);
}
