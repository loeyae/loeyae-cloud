package com.loeyae.cloud.task.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loeyae.cloud.task.DTO.QrtzSimpleTriggersCreate;
import com.loeyae.cloud.task.DTO.QrtzSimpleTriggersPrimary;
import com.loeyae.cloud.task.DTO.QrtzSimpleTriggersQuery;
import com.loeyae.cloud.task.DTO.QrtzSimpleTriggersUpdate;
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
import com.loeyae.cloud.task.VO.QrtzSimpleTriggersView;
import com.loeyae.cloud.task.api.QrtzSimpleTriggersApi;
import com.loeyae.cloud.task.entity.QrtzSimpleTriggers;
import com.loeyae.cloud.task.service.IQrtzSimpleTriggersService;

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
@RequestMapping("/task/qrtz-simple-triggers")
public class QrtzSimpleTriggersController implements QrtzSimpleTriggersApi {

    @Autowired
    IQrtzSimpleTriggersService qrtzSimpleTriggersService;

    /**
     * Create
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzSimpleTriggersView> create(QrtzSimpleTriggersCreate data) {
        ValidateUtil.validateEntity(data);
        QrtzSimpleTriggers entity = BeanUtils.copyToEntity(data, QrtzSimpleTriggers.class);
        qrtzSimpleTriggersService.save(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzSimpleTriggersView.class));
    }

    /**
     * Update
     *
     * @param id
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzSimpleTriggersView> update(int id, QrtzSimpleTriggersUpdate data) {
        ValidateUtil.validateParameter(QrtzSimpleTriggersPrimary.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data);
        QrtzSimpleTriggers entity = BeanUtils.copyToEntity(data, QrtzSimpleTriggers.class);
        entity.setId(id);
        qrtzSimpleTriggersService.updateById(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzSimpleTriggersView.class));
    }

    /**
     * Get
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult<QrtzSimpleTriggersView> get(int id)
    {
        QrtzSimpleTriggers entity = qrtzSimpleTriggersService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzSimpleTriggersView.class));
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
        boolean ret = qrtzSimpleTriggersService.removeById(id);
        return ApiResult.ok(ret ? 1 : 0);
    }

    /**
     * One
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzSimpleTriggersView> one(QrtzSimpleTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzSimpleTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzSimpleTriggers.class);
        List<QrtzSimpleTriggers> entities = qrtzSimpleTriggersService.list(queryWrapper);
        if (!entities.isEmpty()) {
            return ApiResult.ok(BeanUtils.copyToEntity(entities.get(0), QrtzSimpleTriggersView.class));
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
    public ApiResult<PageResult<QrtzSimpleTriggersView>> page(QrtzSimpleTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzSimpleTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzSimpleTriggers.class);
        Page<QrtzSimpleTriggers> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<QrtzSimpleTriggers> pageResult = qrtzSimpleTriggersService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, QrtzSimpleTriggersView.class));
    }

    /**
     * All
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<List<QrtzSimpleTriggersView>> all(QrtzSimpleTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzSimpleTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzSimpleTriggers.class);
        List<QrtzSimpleTriggers> result = qrtzSimpleTriggersService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, QrtzSimpleTriggersView.class));
    }
}
