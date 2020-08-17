package com.loeyae.cloud.task.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loeyae.cloud.task.DTO.QrtzSchedulerStateCreate;
import com.loeyae.cloud.task.DTO.QrtzSchedulerStatePrimary;
import com.loeyae.cloud.task.DTO.QrtzSchedulerStateQuery;
import com.loeyae.cloud.task.DTO.QrtzSchedulerStateUpdate;
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
import com.loeyae.cloud.task.VO.QrtzSchedulerStateView;
import com.loeyae.cloud.task.api.QrtzSchedulerStateApi;
import com.loeyae.cloud.task.entity.QrtzSchedulerState;
import com.loeyae.cloud.task.service.IQrtzSchedulerStateService;

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
@RequestMapping("/task/qrtz-scheduler-state")
public class QrtzSchedulerStateController implements QrtzSchedulerStateApi {

    @Autowired
    IQrtzSchedulerStateService qrtzSchedulerStateService;

    /**
     * Create
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzSchedulerStateView> create(QrtzSchedulerStateCreate data) {
        ValidateUtil.validateEntity(data);
        QrtzSchedulerState entity = BeanUtils.copyToEntity(data, QrtzSchedulerState.class);
        qrtzSchedulerStateService.save(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzSchedulerStateView.class));
    }

    /**
     * Update
     *
     * @param id
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzSchedulerStateView> update(int id, QrtzSchedulerStateUpdate data) {
        ValidateUtil.validateParameter(QrtzSchedulerStatePrimary.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data);
        QrtzSchedulerState entity = BeanUtils.copyToEntity(data, QrtzSchedulerState.class);
        entity.setId(id);
        qrtzSchedulerStateService.updateById(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzSchedulerStateView.class));
    }

    /**
     * Get
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult<QrtzSchedulerStateView> get(int id)
    {
        QrtzSchedulerState entity = qrtzSchedulerStateService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzSchedulerStateView.class));
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
        boolean ret = qrtzSchedulerStateService.removeById(id);
        return ApiResult.ok(ret ? 1 : 0);
    }

    /**
     * One
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzSchedulerStateView> one(QrtzSchedulerStateQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzSchedulerState> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzSchedulerState.class);
        List<QrtzSchedulerState> entities = qrtzSchedulerStateService.list(queryWrapper);
        if (!entities.isEmpty()) {
            return ApiResult.ok(BeanUtils.copyToEntity(entities.get(0), QrtzSchedulerStateView.class));
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
    public ApiResult<PageResult<QrtzSchedulerStateView>> page(QrtzSchedulerStateQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzSchedulerState> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzSchedulerState.class);
        Page<QrtzSchedulerState> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<QrtzSchedulerState> pageResult = qrtzSchedulerStateService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, QrtzSchedulerStateView.class));
    }

    /**
     * All
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<List<QrtzSchedulerStateView>> all(QrtzSchedulerStateQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzSchedulerState> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzSchedulerState.class);
        List<QrtzSchedulerState> result = qrtzSchedulerStateService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, QrtzSchedulerStateView.class));
    }
}
