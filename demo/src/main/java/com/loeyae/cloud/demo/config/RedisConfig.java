package com.loeyae.cloud.demo.config;

import com.loeyae.cloud.commons.redis.RedisConfigAdapter;
import com.loeyae.cloud.commons.redis.RedisService;
import com.loeyae.cloud.commons.redis.RedisServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redis Configuration.
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020-07-11
 */
@Configuration
public class RedisConfig extends RedisConfigAdapter {

    public static final String PREFIX = "loeyae_demo";

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