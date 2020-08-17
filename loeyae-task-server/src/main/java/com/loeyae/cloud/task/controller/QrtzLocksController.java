package com.loeyae.cloud.task.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loeyae.cloud.task.DTO.QrtzLocksCreate;
import com.loeyae.cloud.task.DTO.QrtzLocksPrimary;
import com.loeyae.cloud.task.DTO.QrtzLocksQuery;
import com.loeyae.cloud.task.DTO.QrtzLocksUpdate;
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
import com.loeyae.cloud.task.VO.QrtzLocksView;
import com.loeyae.cloud.task.api.QrtzLocksApi;
import com.loeyae.cloud.task.entity.QrtzLocks;
import com.loeyae.cloud.task.service.IQrtzLocksService;

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
@RequestMapping("/task/qrtz-locks")
public class QrtzLocksController implements QrtzLocksApi {

    @Autowired
    IQrtzLocksService qrtzLocksService;

    /**
     * Create
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzLocksView> create(QrtzLocksCreate data) {
        ValidateUtil.validateEntity(data);
        QrtzLocks entity = BeanUtils.copyToEntity(data, QrtzLocks.class);
        qrtzLocksService.save(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzLocksView.class));
    }

    /**
     * Update
     *
     * @param id
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzLocksView> update(int id, QrtzLocksUpdate data) {
        ValidateUtil.validateParameter(QrtzLocksPrimary.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data);
        QrtzLocks entity = BeanUtils.copyToEntity(data, QrtzLocks.class);
        entity.setId(id);
        qrtzLocksService.updateById(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzLocksView.class));
    }

    /**
     * Get
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult<QrtzLocksView> get(int id)
    {
        QrtzLocks entity = qrtzLocksService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzLocksView.class));
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
        boolean ret = qrtzLocksService.removeById(id);
        return ApiResult.ok(ret ? 1 : 0);
    }

    /**
     * One
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzLocksView> one(QrtzLocksQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzLocks> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzLocks.class);
        List<QrtzLocks> entities = qrtzLocksService.list(queryWrapper);
        if (!entities.isEmpty()) {
            return ApiResult.ok(BeanUtils.copyToEntity(entities.get(0), QrtzLocksView.class));
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
    public ApiResult<PageResult<QrtzLocksView>> page(QrtzLocksQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzLocks> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzLocks.class);
        Page<QrtzLocks> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<QrtzLocks> pageResult = qrtzLocksService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, QrtzLocksView.class));
    }

    /**
     * All
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<List<QrtzLocksView>> all(QrtzLocksQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzLocks> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzLocks.class);
        List<QrtzLocks> result = qrtzLocksService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, QrtzLocksView.class));
    }
}
