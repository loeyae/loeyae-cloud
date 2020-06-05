package com.loeyae.cloud.config;

import com.loeyae.cloud.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FilterConfiguration.
 *
 * @date 2020-04-26
 * @version 1.0
 * @author zhangyi07@beyondsoft.com
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter(skipTokenUrls);
    }
}