/*
 * Copyright (c) 2019. Beyondsoft Corporation.
 * All Rights Reserved.
 *
 * BEYONDSOFT CONFIDENTIALITY
 *
 * The information in this file is confidential and privileged from Beyondsoft Corporation,
 * which is intended only for use solely by Beyondsoft Corporation.
 * Disclosure, copying, distribution, or use of the contents of this file by persons other than Beyondsoft Corporation
 * is strictly prohibited and may violate applicable laws.
 */

package com.loeyae.cloud.task.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.loeyae.cloud.commons.config.MetaObjectHandlerConfig;
import com.loeyae.cloud.commons.config.MybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/7/10 15:07
 */
@Configuration
@MapperScan("com.loeyae.cloud.*.mapper")
public class MPConfig extends MybatisPlusConfig {

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandlerConfig();
    }
}
