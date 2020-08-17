package com.loeyae.cloud.task.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loeyae.cloud.task.DTO.QrtzPausedTriggerGrpsCreate;
import com.loeyae.cloud.task.DTO.QrtzPausedTriggerGrpsPrimary;
import com.loeyae.cloud.task.DTO.QrtzPausedTriggerGrpsQuery;
import com.loeyae.cloud.task.DTO.QrtzPausedTriggerGrpsUpdate;
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
import com.loeyae.cloud.task.VO.QrtzPausedTriggerGrpsView;
import com.loeyae.cloud.task.api.QrtzPausedTriggerGrpsApi;
import com.loeyae.cloud.task.entity.QrtzPausedTriggerGrps;
import com.loeyae.cloud.task.service.IQrtzPausedTriggerGrpsService;

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
@RequestMapping("/task/qrtz-paused-trigger-grps")
public class QrtzPausedTriggerGrpsController implements QrtzPausedTriggerGrpsApi {

    @Autowired
    IQrtzPausedTriggerGrpsService qrtzPausedTriggerGrpsService;

    /**
     * Create
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzPausedTriggerGrpsView> create(QrtzPausedTriggerGrpsCreate data) {
        ValidateUtil.validateEntity(data);
        QrtzPausedTriggerGrps entity = BeanUtils.copyToEntity(data, QrtzPausedTriggerGrps.class);
        qrtzPausedTriggerGrpsService.save(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzPausedTriggerGrpsView.class));
    }

    /**
     * Update
     *
     * @param id
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzPausedTriggerGrpsView> update(int id, QrtzPausedTriggerGrpsUpdate data) {
        ValidateUtil.validateParameter(QrtzPausedTriggerGrpsPrimary.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data);
        QrtzPausedTriggerGrps entity = BeanUtils.copyToEntity(data, QrtzPausedTriggerGrps.class);
        entity.setId(id);
        qrtzPausedTriggerGrpsService.updateById(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzPausedTriggerGrpsView.class));
    }

    /**
     * Get
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult<QrtzPausedTriggerGrpsView> get(int id)
    {
        QrtzPausedTriggerGrps entity = qrtzPausedTriggerGrpsService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzPausedTriggerGrpsView.class));
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
        boolean ret = qrtzPausedTriggerGrpsService.removeById(id);
        return ApiResult.ok(ret ? 1 : 0);
    }

    /**
     * One
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzPausedTriggerGrpsView> one(QrtzPausedTriggerGrpsQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzPausedTriggerGrps> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzPausedTriggerGrps.class);
        List<QrtzPausedTriggerGrps> entities = qrtzPausedTriggerGrpsService.list(queryWrapper);
        if (!entities.isEmpty()) {
            return ApiResult.ok(BeanUtils.copyToEntity(entities.get(0), QrtzPausedTriggerGrpsView.class));
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
    public ApiResult<PageResult<QrtzPausedTriggerGrpsView>> page(QrtzPausedTriggerGrpsQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzPausedTriggerGrps> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzPausedTriggerGrps.class);
        Page<QrtzPausedTriggerGrps> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<QrtzPausedTriggerGrps> pageResult = qrtzPausedTriggerGrpsService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, QrtzPausedTriggerGrpsView.class));
    }

    /**
     * All
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<List<QrtzPausedTriggerGrpsView>> all(QrtzPausedTriggerGrpsQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzPausedTriggerGrps> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzPausedTriggerGrps.class);
        List<QrtzPausedTriggerGrps> result = qrtzPausedTriggerGrpsService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, QrtzPausedTriggerGrpsView.class));
    }
}
