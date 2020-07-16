package com.loeyae.cloud.demo.controller.bo;

import com.loeyae.cloud.commons.validation.constraints.Exists;
import com.loeyae.cloud.demo.service.ITestService;

import javax.validation.constraints.PositiveOrZero;

/**
 * TestPrimary.
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020-07-12
 */
public class TestPrimary extends com.loeyae.cloud.demo.DTO.TestPrimary {


    /**
     * ID
     */
    @PositiveOrZero
    @Exists(service = ITestService.class)
    private Integer id;
}