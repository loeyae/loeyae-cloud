package com.loeyae.cloud.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.commons.tool.BeanUtils;
import com.loeyae.cloud.commons.tool.QueryWapperUtils;
import com.loeyae.cloud.commons.tool.ValidateUtil;
import com.loeyae.cloud.commons.validation.*;
import com.loeyae.cloud.demo.DTO.TestCreate;
import com.loeyae.cloud.demo.DTO.TestQuery;
import com.loeyae.cloud.demo.DTO.TestUpdate;
import com.loeyae.cloud.demo.TestApi;
import com.loeyae.cloud.demo.VO.TestView;
import com.loeyae.cloud.demo.controller.BO.TestParam;
import com.loeyae.cloud.demo.entity.Test;
import com.loeyae.cloud.demo.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang yi
 * @since 2020-06-27
 */
@RestController
@Slf4j
public class TestController implements TestApi {

    @Autowired
    ITestService testService;

    /**
     * Create
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<TestView> create(@Validated TestCreate data) {
        ValidateUtil.validateEntity(data, TestParam.class, Insert.class);
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
    @Override
    public ApiResult<TestView> update(int id, TestUpdate data) {
        ValidateUtil.validateParamter(TestCreate.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data, TestParam.class, Update.class);
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
    @Override
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
    @Override
    public ApiResult<Integer> delete(int id)
    {
        boolean ret = testService.removeById(id);
        return ApiResult.ok(ret ? 1 : 0);
    }

    /**
     * One
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<TestView> one(TestQuery data)
    {
        QueryWrapper<Test> queryWrapper = QueryWapperUtils.queryToWrapper(data, Test.class);
        Test entity = testService.getOne(queryWrapper);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, TestView.class));
    }

    /**
     * Page
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<PageResult<TestView>> page(TestQuery data)
    {
        QueryWrapper<Test> queryWrapper = QueryWapperUtils.queryToWrapper(data, Test.class);
        Page<Test> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<Test> pageResult = testService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, TestView.class));
    }

    /**
     * List
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<List<TestView>> list(TestQuery data)
    {
        QueryWrapper<Test> queryWrapper = QueryWapperUtils.queryToWrapper(data, Test.class);
        List<Test> result = testService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, TestView.class));
    }

}
