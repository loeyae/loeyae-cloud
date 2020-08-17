package com.loeyae.cloud.task.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.task.DTO.QrtzLocksCreate;
import com.loeyae.cloud.task.DTO.QrtzLocksQuery;
import com.loeyae.cloud.task.DTO.QrtzLocksUpdate;
import com.loeyae.cloud.task.VO.QrtzLocksView;
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
public interface QrtzLocksApi {

    @PostMapping("/")
    ApiResult<QrtzLocksView> create(@RequestBody QrtzLocksCreate data);

    @PutMapping("/{id}")
    ApiResult<QrtzLocksView> update(@PathVariable("id") int id, @RequestBody QrtzLocksUpdate data);

    @GetMapping("/{id}")
    ApiResult<QrtzLocksView> get(@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/")
    ApiResult<QrtzLocksView> one(QrtzLocksQuery data);

    @GetMapping("/all/")
    ApiResult<List<QrtzLocksView>> all(QrtzLocksQuery data);

    @GetMapping("/page/")
    ApiResult<PageResult<QrtzLocksView>> page(QrtzLocksQuery data);
}
