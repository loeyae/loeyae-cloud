package com.loeyae.cloud.config;

import com.loeyae.cloud.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${loeyae.skipTokenUrls}")
    private String[] skipTokenUrls;

    @Value("${loeyae.jwtSecretKey}")
    private String jwtSecretKey;

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter(skipTokenUrls, jwtSecretKey);
    }
}