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

package com.loeyae.cloud.message.config.mvc;

import com.loeyae.cloud.commons.config.MvcConfigAdapter;
import feign.Logger;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/7/10 15:07
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
