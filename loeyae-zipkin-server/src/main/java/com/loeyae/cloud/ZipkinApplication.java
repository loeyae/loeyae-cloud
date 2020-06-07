package com.loeyae.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * ZipkinApplication
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/7 13:47
 */
@SpringBootApplication
@EnableZipkinServer
public class ZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class, args);
//        new SpringApplicationBuilder(ZipkinApplication.class)
//                .listeners(new RegisterZipkinHealthIndicators())
//                .properties("spring.config.name=loeyae-zipkin-server")
//                //.properties("spring.config.location=classpath:/springcloud/zipkin-server.yml").run(args);
//                .run(args);
    }
}
