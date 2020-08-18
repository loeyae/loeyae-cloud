package com.loeyae.cloud.task.config;

import com.loeyae.cloud.commons.config.MvcConfigAdapter;
import feign.Contract;
import feign.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FeignClientConfig.
 *
 * @date: 2020-08-18
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
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

//    /**
//     * feign 序列化方式
//     */
//    @Bean
//    public Encoder feignEncoder() {
//        return new SpringEncoder(feignHttpMessageConverter());
//    }

    private ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        final HttpMessageConverters httpMessageConverters =
                new HttpMessageConverters(getFastJsonConverter());
        return () -> httpMessageConverters;
    }

    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

}
