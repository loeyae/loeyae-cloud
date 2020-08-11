package com.loeyae.cloud.message;

import com.loeyae.cloud.commons.tool.SpringContextTool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * MessageApplication.
 *
 * @date: 2020-07-29
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@EnableAutoConfiguration
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableAsync
public class MessageApplication {
    @Bean
    public SpringContextTool springContextTool() {
        return new SpringContextTool();
    }
    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }
}