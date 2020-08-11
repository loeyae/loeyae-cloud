package com.loeyae.cloud.config;

import com.loeyae.cloud.commons.redis.RedisConfigAdapter;
import com.loeyae.cloud.commons.redis.RedisService;
import com.loeyae.cloud.commons.redis.RedisServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RedisConfig.
 *
 * @date: 2020-08-10
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Configuration
public class RedisConfig extends RedisConfigAdapter {

    private static final String PREFIX = "lc:gw";

    @Override
    protected String getPrefix() {
        return PREFIX;
    }

    @Bean
    public RedisService redisService()
    {
        return new RedisServiceImpl();
    }

}