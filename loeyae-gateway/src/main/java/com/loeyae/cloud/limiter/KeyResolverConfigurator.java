package com.loeyae.cloud.limiter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * KeyResolverConfigurator
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/5 16:27
 */
@Configuration
public class KeyResolverConfigurator {

    /**
     * 根据ip地址限制流量
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

	/**
	 * 根据用户限流
	 */
	@Bean
	public KeyResolver userKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getHeaders().getFirst("appId"));
	}

	/**
	 * 根据url进行限流
	 * @return
	 */
	@Bean
	@Primary
	public KeyResolver apiKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getPath().value());
	}

}
