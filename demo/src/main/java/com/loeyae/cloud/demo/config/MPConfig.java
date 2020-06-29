package com.loeyae.cloud.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.loeyae.cloud.commons.config.MetaObjectHandlerConfig;
import com.loeyae.cloud.commons.config.MybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis Plus Config
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/28 16:33
 */
@Configuration
@MapperScan("com.loeyae.cloud.*.mapper")
public class MPConfig extends MybatisPlusConfig {

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandlerConfig();
    }

}
