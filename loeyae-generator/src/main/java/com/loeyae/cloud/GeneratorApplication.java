package com.loeyae.cloud;

import com.loeyae.cloud.generator.MysqlGenerator;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Generator Command Line.
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020-06-27
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class GeneratorApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MysqlGenerator.run(args);
    }
}