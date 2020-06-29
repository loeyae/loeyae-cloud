package com.loeyae.cloud.demo.config;

import feign.Logger;
import feign.codec.Encoder;
import com.loeyae.cloud.commons.config.MvcConfigAdapter;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MVC Config
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/28 16:10
 */
@Configuration
public class MvcConfig extends MvcConfigAdapter {
    /**
     * 打印全部信息
     *
     * @return Logger.Level
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * feign 序列化方式
     */
    @Bean
    public Encoder feignEncoder() {
        return new SpringEncoder(feignHttpMessageConverter());
    }

    private ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        final HttpMessageConverters httpMessageConverters =
                new HttpMessageConverters(getFastJsonConverter());
        return () -> httpMessageConverters;
    }
}
