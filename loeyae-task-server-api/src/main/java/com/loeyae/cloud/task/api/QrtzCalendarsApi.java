package com.loeyae.cloud.task.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.task.DTO.QrtzCalendarsCreate;
import com.loeyae.cloud.task.DTO.QrtzCalendarsQuery;
import com.loeyae.cloud.task.DTO.QrtzCalendarsUpdate;
import com.loeyae.cloud.task.VO.QrtzCalendarsView;
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
public interface QrtzCalendarsApi {

    @PostMapping("/")
    ApiResult<QrtzCalendarsView> create(@RequestBody QrtzCalendarsCreate data);

    @PutMapping("/{id}")
    ApiResult<QrtzCalendarsView> update(@PathVariable("id") int id, @RequestBody QrtzCalendarsUpdate data);

    @GetMapping("/{id}")
    ApiResult<QrtzCalendarsView> get(@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/")
    ApiResult<QrtzCalendarsView> one(QrtzCalendarsQuery data);

    @GetMapping("/all/")
    ApiResult<List<QrtzCalendarsView>> all(QrtzCalendarsQuery data);

    @GetMapping("/page/")
    ApiResult<PageResult<QrtzCalendarsView>> page(QrtzCalendarsQuery data);
}
