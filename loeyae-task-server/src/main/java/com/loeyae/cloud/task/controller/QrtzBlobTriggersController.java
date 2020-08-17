package com.loeyae.cloud.task.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loeyae.cloud.task.DTO.QrtzBlobTriggersCreate;
import com.loeyae.cloud.task.DTO.QrtzBlobTriggersPrimary;
import com.loeyae.cloud.task.DTO.QrtzBlobTriggersQuery;
import com.loeyae.cloud.task.DTO.QrtzBlobTriggersUpdate;
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
import com.loeyae.cloud.task.VO.QrtzBlobTriggersView;
import com.loeyae.cloud.task.api.QrtzBlobTriggersApi;
import com.loeyae.cloud.task.entity.QrtzBlobTriggers;
import com.loeyae.cloud.task.service.IQrtzBlobTriggersService;

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
@RequestMapping("/task/qrtz-blob-triggers")
public class QrtzBlobTriggersController implements QrtzBlobTriggersApi {

    @Autowired
    IQrtzBlobTriggersService qrtzBlobTriggersService;

    /**
     * Create
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzBlobTriggersView> create(QrtzBlobTriggersCreate data) {
        ValidateUtil.validateEntity(data);
        QrtzBlobTriggers entity = BeanUtils.copyToEntity(data, QrtzBlobTriggers.class);
        qrtzBlobTriggersService.save(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzBlobTriggersView.class));
    }

    /**
     * Update
     *
     * @param id
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzBlobTriggersView> update(int id, QrtzBlobTriggersUpdate data) {
        ValidateUtil.validateParameter(QrtzBlobTriggersPrimary.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data);
        QrtzBlobTriggers entity = BeanUtils.copyToEntity(data, QrtzBlobTriggers.class);
        entity.setId(id);
        qrtzBlobTriggersService.updateById(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzBlobTriggersView.class));
    }

    /**
     * Get
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult<QrtzBlobTriggersView> get(int id)
    {
        QrtzBlobTriggers entity = qrtzBlobTriggersService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzBlobTriggersView.class));
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
        boolean ret = qrtzBlobTriggersService.removeById(id);
        return ApiResult.ok(ret ? 1 : 0);
    }

    /**
     * One
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzBlobTriggersView> one(QrtzBlobTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzBlobTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzBlobTriggers.class);
        List<QrtzBlobTriggers> entities = qrtzBlobTriggersService.list(queryWrapper);
        if (!entities.isEmpty()) {
            return ApiResult.ok(BeanUtils.copyToEntity(entities.get(0), QrtzBlobTriggersView.class));
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
    public ApiResult<PageResult<QrtzBlobTriggersView>> page(QrtzBlobTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzBlobTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzBlobTriggers.class);
        Page<QrtzBlobTriggers> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<QrtzBlobTriggers> pageResult = qrtzBlobTriggersService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, QrtzBlobTriggersView.class));
    }

    /**
     * All
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<List<QrtzBlobTriggersView>> all(QrtzBlobTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzBlobTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzBlobTriggers.class);
        List<QrtzBlobTriggers> result = qrtzBlobTriggersService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, QrtzBlobTriggersView.class));
    }
}
