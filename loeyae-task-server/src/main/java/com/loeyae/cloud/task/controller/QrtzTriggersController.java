package com.loeyae.cloud.task.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loeyae.cloud.task.DTO.QrtzTriggersCreate;
import com.loeyae.cloud.task.DTO.QrtzTriggersPrimary;
import com.loeyae.cloud.task.DTO.QrtzTriggersQuery;
import com.loeyae.cloud.task.DTO.QrtzTriggersUpdate;
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
import com.loeyae.cloud.task.VO.QrtzTriggersView;
import com.loeyae.cloud.task.api.QrtzTriggersApi;
import com.loeyae.cloud.task.entity.QrtzTriggers;
import com.loeyae.cloud.task.service.IQrtzTriggersService;

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
@RequestMapping("/task/qrtz-triggers")
public class QrtzTriggersController implements QrtzTriggersApi {

    @Autowired
    IQrtzTriggersService qrtzTriggersService;

    /**
     * Create
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzTriggersView> create(QrtzTriggersCreate data) {
        ValidateUtil.validateEntity(data);
        QrtzTriggers entity = BeanUtils.copyToEntity(data, QrtzTriggers.class);
        qrtzTriggersService.save(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzTriggersView.class));
    }

    /**
     * Update
     *
     * @param id
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzTriggersView> update(int id, QrtzTriggersUpdate data) {
        ValidateUtil.validateParameter(QrtzTriggersPrimary.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data);
        QrtzTriggers entity = BeanUtils.copyToEntity(data, QrtzTriggers.class);
        entity.setId(id);
        qrtzTriggersService.updateById(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzTriggersView.class));
    }

    /**
     * Get
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult<QrtzTriggersView> get(int id)
    {
        QrtzTriggers entity = qrtzTriggersService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzTriggersView.class));
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
        boolean ret = qrtzTriggersService.removeById(id);
        return ApiResult.ok(ret ? 1 : 0);
    }

    /**
     * One
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzTriggersView> one(QrtzTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzTriggers.class);
        List<QrtzTriggers> entities = qrtzTriggersService.list(queryWrapper);
        if (!entities.isEmpty()) {
            return ApiResult.ok(BeanUtils.copyToEntity(entities.get(0), QrtzTriggersView.class));
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
    public ApiResult<PageResult<QrtzTriggersView>> page(QrtzTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzTriggers.class);
        Page<QrtzTriggers> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<QrtzTriggers> pageResult = qrtzTriggersService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, QrtzTriggersView.class));
    }

    /**
     * All
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<List<QrtzTriggersView>> all(QrtzTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzTriggers.class);
        List<QrtzTriggers> result = qrtzTriggersService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, QrtzTriggersView.class));
    }
}
