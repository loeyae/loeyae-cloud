package com.loeyae.cloud.files;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.loeyae.cloud.commons.tool.SpringContextTool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * FileApplication
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/6 18:01
 */
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FileApplication {
    @Bean
    public SpringContextTool springContextTool() {
        return new SpringContextTool();
    }
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
}
