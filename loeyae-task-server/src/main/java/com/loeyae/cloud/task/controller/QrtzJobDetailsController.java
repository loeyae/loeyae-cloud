package com.loeyae.cloud.task.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loeyae.cloud.task.DTO.QrtzJobDetailsCreate;
import com.loeyae.cloud.task.DTO.QrtzJobDetailsPrimary;
import com.loeyae.cloud.task.DTO.QrtzJobDetailsQuery;
import com.loeyae.cloud.task.DTO.QrtzJobDetailsUpdate;
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
import com.loeyae.cloud.task.VO.QrtzJobDetailsView;
import com.loeyae.cloud.task.api.QrtzJobDetailsApi;
import com.loeyae.cloud.task.entity.QrtzJobDetails;
import com.loeyae.cloud.task.service.IQrtzJobDetailsService;

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
@RequestMapping("/task/qrtz-job-details")
public class QrtzJobDetailsController implements QrtzJobDetailsApi {

    @Autowired
    IQrtzJobDetailsService qrtzJobDetailsService;

    /**
     * Create
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzJobDetailsView> create(QrtzJobDetailsCreate data) {
        ValidateUtil.validateEntity(data);
        QrtzJobDetails entity = BeanUtils.copyToEntity(data, QrtzJobDetails.class);
        qrtzJobDetailsService.save(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzJobDetailsView.class));
    }

    /**
     * Update
     *
     * @param id
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzJobDetailsView> update(int id, QrtzJobDetailsUpdate data) {
        ValidateUtil.validateParameter(QrtzJobDetailsPrimary.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data);
        QrtzJobDetails entity = BeanUtils.copyToEntity(data, QrtzJobDetails.class);
        entity.setId(id);
        qrtzJobDetailsService.updateById(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzJobDetailsView.class));
    }

    /**
     * Get
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult<QrtzJobDetailsView> get(int id)
    {
        QrtzJobDetails entity = qrtzJobDetailsService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, QrtzJobDetailsView.class));
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
        boolean ret = qrtzJobDetailsService.removeById(id);
        return ApiResult.ok(ret ? 1 : 0);
    }

    /**
     * One
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<QrtzJobDetailsView> one(QrtzJobDetailsQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzJobDetails> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzJobDetails.class);
        List<QrtzJobDetails> entities = qrtzJobDetailsService.list(queryWrapper);
        if (!entities.isEmpty()) {
            return ApiResult.ok(BeanUtils.copyToEntity(entities.get(0), QrtzJobDetailsView.class));
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
    public ApiResult<PageResult<QrtzJobDetailsView>> page(QrtzJobDetailsQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzJobDetails> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzJobDetails.class);
        Page<QrtzJobDetails> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<QrtzJobDetails> pageResult = qrtzJobDetailsService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, QrtzJobDetailsView.class));
    }

    /**
     * All
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<List<QrtzJobDetailsView>> all(QrtzJobDetailsQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<QrtzJobDetails> queryWrapper = QueryWapperUtils.queryToWrapper(data, QrtzJobDetails.class);
        List<QrtzJobDetails> result = qrtzJobDetailsService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, QrtzJobDetailsView.class));
    }
}
