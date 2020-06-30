package com.loeyae.cloud.demo.api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.demo.DTO.*;
import com.loeyae.cloud.demo.VO.TestView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Test APi
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/28 16:40
 */
public interface TestApi {

    @PostMapping("/api/test")
    ApiResult<TestView> create(@RequestBody TestCreate data);

    @PutMapping("/api/test/{id}")
    ApiResult<TestView> update(@PathVariable("id") int id, @RequestBody TestUpdate data);

    @GetMapping("/api/test/{id}")
    ApiResult<TestView> get(@PathVariable("id") int id);

    @DeleteMapping("/api/test/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/api/test")
    ApiResult<TestView> one(TestQuery data);

    @GetMapping("/api/test/all")
    ApiResult<List<TestView>> all(TestQuery data);

    @GetMapping("/api/test/page")
    ApiResult<PageResult<TestView>> page(TestQuery data);
}
