package com.loeyae.cloud;

import com.loeyae.cloud.commons.tool.SpringContextTool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * GatewayApplication
 *
 * @date 2019-12-24
 * @version 1.0
 * @author zhangyi07@beyondsoft.com
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class GatewayApplication {

	@Bean
	public SpringContextTool springContextTool() {
		return new SpringContextTool();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
