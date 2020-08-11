package com.loeyae.cloud.config;

import com.loeyae.cloud.filter.PermissionFilter;
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

    @Value("${loeyae.verifyTokenUrls}")
    private String[] verifyTokenUrls;

    @Value("${loeyae.skipTokenUrls}")
    private String[] skipTokenUrls;

    @Value("${loeyae.skipExcludeUrls}")
    private String[] skipExcludeUrls;

    @Value("${loeyae.jwtSecretKey}")
    private String jwtSecretKey;

    @Value("${loeyae.identifiedId}")
    private String identifiedId;

    @Value("${loeyae.permissionEnabled}")
    private boolean permissionEnabled;

    @Value("${loeyae.permissionCachedEnabled}")
    private boolean permissionCachedEnabled;

    @Value("${loeyae.permissionCachePrefix}")
    private String permissionCachePrefix;

    @Value("${loeyae.permissionCacheExpire}")
    private int permissionCacheExpire;


    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter(verifyTokenUrls, skipTokenUrls, skipExcludeUrls, jwtSecretKey, identifiedId);
    }

    @Bean
    public PermissionFilter permissionFilter()
    {
        return new PermissionFilter(permissionEnabled, permissionCachedEnabled,
                permissionCachePrefix, permissionCacheExpire);
    }

}