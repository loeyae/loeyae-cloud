package com.loeyae.cloud.demo.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.demo.DTO.*;
import com.loeyae.cloud.demo.VO.MenuView;
import com.loeyae.cloud.demo.VO.PermissionView;
import com.loeyae.cloud.demo.VO.TestView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  接口定义
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-06-30
 */
public interface TestApi {

    @PostMapping("/")
    ApiResult<TestView> create(@RequestBody TestCreate data);

    @PutMapping("/{id}")
    ApiResult<TestView> update(@PathVariable("id") int id, @RequestBody TestUpdate data);

    @GetMapping("/{id}")
    ApiResult<TestView> get(@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/")
    ApiResult<TestView> one(TestQuery data);

    @GetMapping("/all/")
    ApiResult<List<TestView>> all(TestQuery data);

    @GetMapping("/page/")
    ApiResult<PageResult<TestView>> page(TestQuery data);

    @GetMapping("/menu/")
    ApiResult<List<MenuView>> getMenuList(String userId);

    @GetMapping("/permission/")
    ApiResult<List<PermissionView>> getPermissionList(String userId);
}
