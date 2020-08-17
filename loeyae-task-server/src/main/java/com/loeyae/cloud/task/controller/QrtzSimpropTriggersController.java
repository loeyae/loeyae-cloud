package com.loeyae.cloud.task.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loeyae.cloud.task.DTO.QrtzSimpropTriggersCreate;
import com.loeyae.cloud.task.DTO.QrtzSimpropTriggersPrimary;
import com.loeyae.cloud.task.DTO.QrtzSimpropTriggersQuery;
import com.loeyae.cloud.task.DTO.QrtzSimpropTriggersUpdate;
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
import com.loeyae.cloud.task.VO.QrtzSimpropTriggersView;
import com.loeyae.cloud.task.api.QrtzSimpropTriggersApi;
import com.loeyae.cloud.task.entity.QrtzSimpropTriggers;
import com.loeyae.cloud.task.service.IQrtzSimpropTriggersService;

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
@RequestMapping("/task/qrtz-simprop-triggers")
public class QrtzSimpropTriggersController implements QrtzSimpropTriggersApi {

    @Autowired
    IQrtzSimpropTriggersService qrtzSimpropTriggersService;

    /**
     * Create
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzSimpropTriggersView> create(QrtzSimpropTriggersCreate data) {
        ValidateUtil.validateEntity(data);
        QrtzSimpropTriggers entity = BeanUtils.copyToEntity(data, QrtzSimpropTriggers.class);
        qrtzSimpropTriggersService.save(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzSimpropTriggersView.class));
    }

    /**
     * Update
     *
     * @param id
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzSimpropTriggersView> update(int id, QrtzSimpropTriggersUpdate data) {
        ValidateUtil.validateParameter(QrtzSimpropTriggersPrimary.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data);
        QrtzSimpropTriggers entity = BeanUtils.copyToEntity(data, QrtzSimpropTriggers.class);
        entity.setId(id);
        qrtzSimpropTriggersService.updateById(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzSimpropTriggersView.class));
    }

    /**
     * Get
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult<QrtzSimpropTriggersView> get(int id)
    {
        QrtzSimpropTriggers entity = qrtzSimpropTriggersService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzSimpropTriggersView.class));
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
        boolean ret = qrtzSimpropTriggersService.removeById(id);
        return ApiResult.ok(ret ? 1 : 0);
    }

    /**
     * One
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzSimpropTriggersView> one(QrtzSimpropTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzSimpropTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzSimpropTriggers.class);
        List<QrtzSimpropTriggers> entities = qrtzSimpropTriggersService.list(queryWrapper);
        if (!entities.isEmpty()) {
            return ApiResult.ok(BeanUtils.copyToEntity(entities.get(0), QrtzSimpropTriggersView.class));
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
    public ApiResult<PageResult<QrtzSimpropTriggersView>> page(QrtzSimpropTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzSimpropTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzSimpropTriggers.class);
        Page<QrtzSimpropTriggers> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<QrtzSimpropTriggers> pageResult = qrtzSimpropTriggersService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, QrtzSimpropTriggersView.class));
    }

    /**
     * All
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<List<QrtzSimpropTriggersView>> all(QrtzSimpropTriggersQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzSimpropTriggers> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzSimpropTriggers.class);
        List<QrtzSimpropTriggers> result = qrtzSimpropTriggersService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, QrtzSimpropTriggersView.class));
    }
}
