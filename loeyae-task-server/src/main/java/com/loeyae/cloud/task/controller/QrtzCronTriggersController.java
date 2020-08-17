package com.loeyae.cloud.task.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loeyae.cloud.task.DTO.QrtzCronTriggersCreate;
import com.loeyae.cloud.task.DTO.QrtzCronTriggersPrimary;
import com.loeyae.cloud.task.DTO.QrtzCronTriggersQuery;
import com.loeyae.cloud.task.DTO.QrtzCronTriggersUpdate;
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
import com.loeyae.cloud.task.VO.QrtzCronTriggersView;
import com.loeyae.cloud.task.api.QrtzCronTriggersApi;
import com.loeyae.cloud.task.entity.QrtzCronTriggers;
import com.loeyae.cloud.task.service.IQrtzCronTriggersService;

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
@RequestMapping("/task/qrtz-cron-triggers")
public class QrtzCronTriggersController implements QrtzCronTriggersApi {

    @Autowired
    IQrtzCronTriggersService qrtzCronTriggersService;

    /**
     * Create
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzCronTriggersView> create(QrtzCronTriggersCreate data) {
        ValidateUtil.validateEntity(data);
        QrtzCronTriggers entity = BeanUtils.copyToEntity(data, QrtzCronTriggers.class);
        qrtzCronTriggersService.save(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzCronTriggersView.class));
    }

    /**
     * Update
     *
     * @param id
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzCronTriggersView> update(int id, QrtzCronTriggersUpdate data) {
        ValidateUtil.validateParameter(QrtzCronTriggersPrimary.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data);
        QrtzCronTriggers entity = BeanUtils.copyToEntity(data, QrtzCronTriggers.class);
        entity.setId(id);
        qrtzCronTriggersService.updateById(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzCronTriggersView.class));
    }

    /**
     * Get
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult<QrtzCronTriggersView> get(int id)
    {
        QrtzCronTriggers entity = qrtzCronTriggersService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzCronTriggersView.class));
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
        boolean ret = qrtzCronTriggersService.removeById(id);
        return ApiResult.ok(ret ? 1 : 0);
    }

    /**
     * One
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzCronTriggersView> one(QrtzCronTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzCronTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzCronTriggers.class);
        List<QrtzCronTriggers> entities = qrtzCronTriggersService.list(queryWrapper);
        if (!entities.isEmpty()) {
            return ApiResult.ok(BeanUtils.copyToEntity(entities.get(0), QrtzCronTriggersView.class));
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
    public ApiResult<PageResult<QrtzCronTriggersView>> page(QrtzCronTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzCronTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzCronTriggers.class);
        Page<QrtzCronTriggers> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<QrtzCronTriggers> pageResult = qrtzCronTriggersService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, QrtzCronTriggersView.class));
    }

    /**
     * All
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<List<QrtzCronTriggersView>> all(QrtzCronTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzCronTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzCronTriggers.class);
        List<QrtzCronTriggers> result = qrtzCronTriggersService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, QrtzCronTriggersView.class));
    }
}
