package com.loeyae.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Service Center main.
 *
 * @date 2019-12-24
 * @version 1.0
 * @author zhangyi07@beyondsoft.com
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCenterApplication.class, args);
    }
}