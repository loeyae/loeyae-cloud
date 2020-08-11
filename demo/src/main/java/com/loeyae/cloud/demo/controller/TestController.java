package com.loeyae.cloud.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loeyae.cloud.commons.redis.RedisService;
import com.loeyae.cloud.demo.VO.MenuView;
import com.loeyae.cloud.demo.VO.PermissionView;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.commons.tool.BeanUtils;
import com.loeyae.cloud.commons.tool.QueryWapperUtils;
import com.loeyae.cloud.commons.tool.ValidateUtil;
import com.loeyae.cloud.commons.validation.*;

import com.loeyae.cloud.demo.DTO.*;
import com.loeyae.cloud.demo.controller.bo.TestPrimary;
import com.loeyae.cloud.demo.VO.TestView;
import com.loeyae.cloud.demo.api.TestApi;
import com.loeyae.cloud.demo.entity.Test;
import com.loeyae.cloud.demo.service.ITestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-06-30
 */
@RestController
@RequestMapping("/demo/test")
public class TestController implements TestApi {

    @Autowired
    ITestService testService;

    @Autowired
    RedisService redisService;

    /**
     * Create
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<TestView> create(TestCreate data) {
        ValidateUtil.validateEntity(data);
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
    @CacheEvict(value = "test-get", key = "#id")
    @Override
    public ApiResult<TestView> update(int id, TestUpdate data) {
        ValidateUtil.validateParameter(TestPrimary.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data);
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
    @Cacheable(value = "test-get", key = "#id")
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
        ValidateUtil.validateEntity(data);
        QueryWrapper<Test> queryWrapper = QueryWapperUtils.queryToWrapper(data, Test.class);
        List<Test> entities = testService.list(queryWrapper);
        if (!entities.isEmpty()) {
            return ApiResult.ok(BeanUtils.copyToEntity(entities.get(0), TestView.class));
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
    public ApiResult<PageResult<TestView>> page(TestQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<Test> queryWrapper = QueryWapperUtils.queryToWrapper(data, Test.class);
        Page<Test> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<Test> pageResult = testService.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, TestView.class));
    }

    @Override
    public ApiResult<List<MenuView>> getMenuList(String userId) {
        List<MenuView> menuViews = new ArrayList<MenuView>() {{
           add(new MenuView(){{
               setUrl("/demo/test/");
               setCode("demo:test:create");
               setMethod(HttpMethod.POST.name());
           }});
           add(new MenuView(){{
               setUrl("/demo/test/{id}");
               setCode("demo:test:update");
               setMethod(HttpMethod.PUT.name());
           }});
            add(new MenuView(){{
                setUrl("/demo/test/{id}");
                setCode("demo:test:get");
            }});
            add(new MenuView(){{
                setUrl("/demo/test/page/");
                setCode("demo:test:page");
            }});
        }};
        return ApiResult.ok(menuViews);
    }

    @Override
    public ApiResult<List<PermissionView>> getPermissionList(String userId) {
        List<PermissionView> permissionViews = new ArrayList<PermissionView>(){{
            add(new PermissionView(){{
                setCode("demo:test:create");
                setAllowed(false);
            }});
            add(new PermissionView(){{
                setCode("demo:test:update");
                setAllowed(false);
            }});
            add(new PermissionView(){{
                setCode("demo:test:get");
                setAllowed(true);
            }});
            add(new PermissionView(){{
                setCode("demo:test:page");
                setAllowed(true);
            }});
        }};
        return ApiResult.ok(permissionViews);
    }

    /**
     * All
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<List<TestView>> all(TestQuery data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<Test> queryWrapper = QueryWapperUtils.queryToWrapper(data, Test.class);
        List<Test> result = testService.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, TestView.class));
    }
}
