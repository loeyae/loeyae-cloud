package com.loeyae.cloud.demo;

import com.loeyae.cloud.commons.tool.SpringContextTool;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

/**
 * Deme Application
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/28 14:09
 */
@SpringBootApplication
public class DemoApplication {

    @Bean
    public static SpringContextTool springContextTool(){
        return new SpringContextTool();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args) ;
    }
}
