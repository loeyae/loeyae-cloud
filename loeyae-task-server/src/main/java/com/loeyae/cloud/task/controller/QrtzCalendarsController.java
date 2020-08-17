package com.loeyae.cloud.task.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loeyae.cloud.task.DTO.QrtzCalendarsCreate;
import com.loeyae.cloud.task.DTO.QrtzCalendarsPrimary;
import com.loeyae.cloud.task.DTO.QrtzCalendarsQuery;
import com.loeyae.cloud.task.DTO.QrtzCalendarsUpdate;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.commons.tool.BeanUtils;
import com.loeyae.cloud.commons.tool.QueryWapperUtils;
import com.loeyae.cloud.commons.tool.ValidateUtil;
import com.loeyae.cloud.commons.validation.*;

import com.loeyae.cloud.task.api.DTO.*;
import com.loeyae.cloud.task.VO.QrtzCalendarsView;
import com.loeyae.cloud.task.api.QrtzCalendarsApi;
import com.loeyae.cloud.task.entity.QrtzCalendars;
import com.loeyae.cloud.task.service.IQrtzCalendarsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/task/qrtz-calendars")
public class QrtzCalendarsController implements QrtzCalendarsApi {

    @Autowired
    IQrtzCalendarsService qrtzCalendarsService;

    /**
     * Create
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzCalendarsView> create(QrtzCalendarsCreate data) {
        ValidateUtil.validateEntity(data);
        QrtzCalendars entity = BeanUtils.copyToEntity(data, QrtzCalendars.class);
        qrtzCalendarsService.save(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzCalendarsView.class));
    }

    /**
     * Update
     *
     * @param id
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzCalendarsView> update(int id, QrtzCalendarsUpdate data) {
        ValidateUtil.validateParameter(QrtzCalendarsPrimary.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data);
        QrtzCalendars entity = BeanUtils.copyToEntity(data, QrtzCalendars.class);
        entity.setId(id);
        qrtzCalendarsService.updateById(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzCalendarsView.class));
    }

    /**
     * Get
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult<QrtzCalendarsView> get(int id)
    {
        QrtzCalendars entity = qrtzCalendarsService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzCalendarsView.class));
    }

    /**
     * Delete
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult<Integer> delete(int id)
    {
        boolean ret = qrtzCalendarsService.removeById(id);
        return ApiResult.ok(ret ? 1 : 0);
    }

    /**
     * One
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzCalendarsView> one(QrtzCalendarsQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzCalendars> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzCalendars.class);
        List<QrtzCalendars> entities = qrtzCalendarsService.list(queryWrapper);
        if (!entities.isEmpty()) {
            return ApiResult.ok(BeanUtils.copyToEntity(entities.get(0), QrtzCalendarsView.class));
        }
        return ApiResult.ok(null);
    }

    /**
     * Page
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<PageResult<QrtzCalendarsView>> page(QrtzCalendarsQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzCalendars> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzCalendars.class);
        Page<QrtzCalendars> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<QrtzCalendars> pageResult = qrtzCalendarsService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, QrtzCalendarsView.class));
    }

    /**
     * All
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<List<QrtzCalendarsView>> all(QrtzCalendarsQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzCalendars> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzCalendars.class);
        List<QrtzCalendars> result = qrtzCalendarsService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, QrtzCalendarsView.class));
    }
}
