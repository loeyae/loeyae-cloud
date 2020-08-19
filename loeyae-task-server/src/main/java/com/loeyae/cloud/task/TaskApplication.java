package com.loeyae.cloud.task;

import com.loeyae.cloud.commons.tool.SpringContextTool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * TaskApplication.
 *
 * @date: 2020-08-17
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@SpringBootApplication
@EnableEurekaClient
public class TaskApplication {

    @Bean
    public SpringContextTool springContextTool() {
        return new SpringContextTool();
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}