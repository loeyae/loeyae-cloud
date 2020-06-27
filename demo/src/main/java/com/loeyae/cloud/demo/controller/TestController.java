package com.loeyae.cloud.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.commons.exception.BaseErrorCode;
import com.loeyae.cloud.commons.tool.BeanUtils;
import com.loeyae.cloud.commons.tool.QueryWapperUtils;
import com.loeyae.cloud.commons.tool.ValidateUtil;
import com.loeyae.cloud.commons.validation.*;
import com.loeyae.cloud.demo.DTO.TestParam;
import com.loeyae.cloud.demo.VO.TestView;
import com.loeyae.cloud.demo.entity.Test;
import com.loeyae.cloud.demo.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang yi
 * @since 2020-06-27
 */
@Controller
@RequestMapping("/demo/test")
public class TestController {

    @Autowired
    private ITestService testService;

    /**
     * Create
     *
     * @param data
     * @return
     */
    public ApiResult<TestView> create(TestParam data)
    {
        ValidateUtil.validateEntity(data, Insert.class);
        Test entity = BeanUtils.copyToEntity(data, Test.class);
        testService.save(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, TestView.class));
    }

    /**
     * Update
     *
     * @param id
     * @param data
     * @return
     */
    public ApiResult<TestView> update(int id, TestParam data)
    {
        ValidateUtil.validateParamter(TestParam.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data, Update.class);
        Test entity = BeanUtils.copyToEntity(data, Test.class);
        entity.setId(id);
        testService.updateById(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, TestView.class));
    }

    /**
     * Get
     *
     * @param id
     * @return
     */
    public ApiResult<TestView> get(int id)
    {
        Test entity = testService.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, TestView.class));
    }

    /**
     * Delete
     *
     * @param id
     * @return
     */
    public ApiResult<Boolean> delete(int id)
    {
        Boolean ret = testService.removeById(id);
        return ApiResult.ok(ret);
    }

    /**
     * One
     *
     * @param data
     * @return
     */
    public ApiResult<TestView> one(TestParam data)
    {
        QueryWrapper<Test> queryWrapper = QueryWapperUtils.queryToWrapper(data, Test.class);
        Test entity = testService.getOne(queryWrapper);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, TestView.class));
    }

    public ApiResult<PageResult<TestView>> page(TestParam data)
    {
        QueryWrapper<Test> queryWrapper = QueryWapperUtils.queryToWrapper(data, Test.class);
        Page<Test> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<Test> pageResult = testService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, TestView.class));
    }

    public ApiResult<List<TestView>> list(TestParam data)
    {
        QueryWrapper<Test> queryWrapper = QueryWapperUtils.queryToWrapper(data, Test.class);
        List<Test> result = testService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, TestView.class));
    }

}
