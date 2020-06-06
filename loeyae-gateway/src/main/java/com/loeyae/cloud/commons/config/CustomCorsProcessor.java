package com.loeyae.cloud.commons.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.DefaultCorsProcessor;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/5 14:25
 */
public class CustomCorsProcessor extends DefaultCorsProcessor {
    /**
     * stomp url
     */
    private String stompUrlInfo;

    public CustomCorsProcessor(String stompUrlInfo) {
        this.stompUrlInfo = stompUrlInfo;
    }

    @Override
    public boolean process(CorsConfiguration config, ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        /*
         * 对于stomp协议 不做跨域处理  由目标服务去处理
         */
        if (request.getPath().toString().contains(stompUrlInfo)) {
            return true;
        }
        return super.process(config, exchange);
    }
}
