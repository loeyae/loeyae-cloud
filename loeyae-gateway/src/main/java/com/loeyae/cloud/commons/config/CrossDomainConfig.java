package com.loeyae.cloud.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * CrossDomainConfig
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/5 14:18
 */
@Configuration
public class CrossDomainConfig {

    private static final String ALL = "*";
    /**
     * options请求缓存1分钟
     */
    private static final long MAX_AGE = 60 * 60L;
    private static final String STOMP_INFO = "/stomp/info";
    private static final String CRE = "/**";

    /**
     * 跨域设置
     * @return
     */
    @Bean
    public CorsWebFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        final CorsConfiguration config = new CorsConfiguration();
        // 允许cookies跨域
        config.setAllowCredentials(true);
        // #允许向该服务器提交请求的URI，*表示全部允许
        config.addAllowedOrigin(ALL);
        // #允许访问的头信息,*表示全部
        config.addAllowedHeader(ALL);
        // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.setMaxAge(MAX_AGE);
        // 允许提交请求的方法，*表示全部允许
        config.addAllowedMethod(ALL);

        source.registerCorsConfiguration(CRE, config);

        CustomCorsProcessor processor = new CustomCorsProcessor(STOMP_INFO);

        return new CorsWebFilter(source, processor);
    }
}
