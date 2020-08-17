package com.loeyae.cloud.task.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loeyae.cloud.task.DTO.QrtzFiredTriggersCreate;
import com.loeyae.cloud.task.DTO.QrtzFiredTriggersPrimary;
import com.loeyae.cloud.task.DTO.QrtzFiredTriggersQuery;
import com.loeyae.cloud.task.DTO.QrtzFiredTriggersUpdate;
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
import com.loeyae.cloud.task.VO.QrtzFiredTriggersView;
import com.loeyae.cloud.task.api.QrtzFiredTriggersApi;
import com.loeyae.cloud.task.entity.QrtzFiredTriggers;
import com.loeyae.cloud.task.service.IQrtzFiredTriggersService;

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
@RequestMapping("/task/qrtz-fired-triggers")
public class QrtzFiredTriggersController implements QrtzFiredTriggersApi {

    @Autowired
    IQrtzFiredTriggersService qrtzFiredTriggersService;

    /**
     * Create
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzFiredTriggersView> create(QrtzFiredTriggersCreate data) {
        ValidateUtil.validateEntity(data);
        QrtzFiredTriggers entity = BeanUtils.copyToEntity(data, QrtzFiredTriggers.class);
        qrtzFiredTriggersService.save(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzFiredTriggersView.class));
    }

    /**
     * Update
     *
     * @param id
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzFiredTriggersView> update(int id, QrtzFiredTriggersUpdate data) {
        ValidateUtil.validateParameter(QrtzFiredTriggersPrimary.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data);
        QrtzFiredTriggers entity = BeanUtils.copyToEntity(data, QrtzFiredTriggers.class);
        entity.setId(id);
        qrtzFiredTriggersService.updateById(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzFiredTriggersView.class));
    }

    /**
     * Get
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult<QrtzFiredTriggersView> get(int id)
    {
        QrtzFiredTriggers entity = qrtzFiredTriggersService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzFiredTriggersView.class));
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
        boolean ret = qrtzFiredTriggersService.removeById(id);
        return ApiResult.ok(ret ? 1 : 0);
    }

    /**
     * One
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzFiredTriggersView> one(QrtzFiredTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzFiredTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzFiredTriggers.class);
        List<QrtzFiredTriggers> entities = qrtzFiredTriggersService.list(queryWrapper);
        if (!entities.isEmpty()) {
            return ApiResult.ok(BeanUtils.copyToEntity(entities.get(0), QrtzFiredTriggersView.class));
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
    public ApiResult<PageResult<QrtzFiredTriggersView>> page(QrtzFiredTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzFiredTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzFiredTriggers.class);
        Page<QrtzFiredTriggers> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<QrtzFiredTriggers> pageResult = qrtzFiredTriggersService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, QrtzFiredTriggersView.class));
    }

    /**
     * All
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<List<QrtzFiredTriggersView>> all(QrtzFiredTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzFiredTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzFiredTriggers.class);
        List<QrtzFiredTriggers> result = qrtzFiredTriggersService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, QrtzFiredTriggersView.class));
    }
}
