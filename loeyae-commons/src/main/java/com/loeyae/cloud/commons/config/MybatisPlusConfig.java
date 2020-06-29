package com.loeyae.cloud.commons.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * MybatisPlusConfig
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/28 16:15
 */
public class MybatisPlusConfig {

    /**
     * 分页
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    /**
     * SQL执行效率插件 生产环境建议删除
     * 设置 dev test 环境开启
     */
    @Bean
    @Profile({"local", "dev","test", "qa"})
    public SqlExplainInterceptor sqlExplainInterceptor() {
        return new SqlExplainInterceptor();
    }

}
