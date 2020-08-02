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

package com.loeyae.cloud.message.config;

import com.loeyae.cloud.commons.redis.RedisConfigAdapter;
import com.loeyae.cloud.commons.redis.RedisService;
import com.loeyae.cloud.commons.redis.RedisServiceImpl;
import org.springframework.context.annotation.Bean;

/**
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/7/10 15:07
 */
//@Configuration
public class RedisConfig extends RedisConfigAdapter {

    public static final String PREFIX = "pgs-message-v1:";

    @Override
    protected String getPrefix() {
        return PREFIX;
    }

    /**
     * 实例化redis工具类
     */
    @Bean
    public RedisService redisService(){
        return new RedisServiceImpl();
    }
}
