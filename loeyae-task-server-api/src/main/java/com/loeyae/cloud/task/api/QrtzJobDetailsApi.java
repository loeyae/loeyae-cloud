package com.loeyae.cloud.task.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.task.DTO.QrtzJobDetailsCreate;
import com.loeyae.cloud.task.DTO.QrtzJobDetailsQuery;
import com.loeyae.cloud.task.DTO.QrtzJobDetailsUpdate;
import com.loeyae.cloud.task.VO.QrtzJobDetailsView;
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
public interface QrtzJobDetailsApi {

    @PostMapping("/")
    ApiResult<QrtzJobDetailsView> create(@RequestBody QrtzJobDetailsCreate data);

    @PutMapping("/{id}")
    ApiResult<QrtzJobDetailsView> update(@PathVariable("id") int id, @RequestBody QrtzJobDetailsUpdate data);

    @GetMapping("/{id}")
    ApiResult<QrtzJobDetailsView> get(@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/")
    ApiResult<QrtzJobDetailsView> one(QrtzJobDetailsQuery data);

    @GetMapping("/all/")
    ApiResult<List<QrtzJobDetailsView>> all(QrtzJobDetailsQuery data);

    @GetMapping("/page/")
    ApiResult<PageResult<QrtzJobDetailsView>> page(QrtzJobDetailsQuery data);
}
