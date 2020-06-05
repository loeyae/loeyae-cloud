package com.loeyae.cloud.filter;

import com.loeyae.cloud.exception.BaseErrorCode;
import com.loeyae.cloud.exception.IErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * TokenFilter
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/4/26 17:50
 */
@Slf4j
@Component
public class TokenFilter implements GlobalFilter, Ordered {

    private static final String JWT_AUTH_KEY = "Authorization";
    Logger logger= LoggerFactory.getLogger( TokenFilter.class );

    @Value("${loeyae.skipTokenUrls}")
    private String[] skipTokenUrls;

    @Value("${loeyae.jwtSecretKey}")
    private String jwtSecretKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        PathMatcher matcher = new AntPathMatcher();
        for (String skipUrl: skipTokenUrls) {
            boolean result = matcher.match(skipUrl, url);
            if (result) {
                return chain.filter(exchange);
            }
        }

        //从请求头中取出token
        String token = exchange.getRequest().getHeaders().getFirst(JWT_AUTH_KEY);
        //未携带token或token在黑名单内
        if (token == null || token.isEmpty() || StringUtils.isBlank(token)) {
            return message(exchange, BaseErrorCode.TOKEN_INVALID);
        }
        try {
            verifyJWT(token);
        } catch (ExpiredJwtException expiredJwtException) {
            return message(exchange, BaseErrorCode.TOKEN_EXPIRE);
        } catch (Exception e) {
            return message(exchange, BaseErrorCode.TOKEN_INVALID);
        }
        return chain.filter(exchange);
    }


    /**
     * 认证错误输出
     * @param resp 响应对象
     * @param message 错误信息
     * @param code 错误码
     * @return
     */
    private Mono<Void> message(ServerHttpResponse resp, String message, int code) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", message);
        DataBuffer buffer = resp.bufferFactory().wrap(toJSONString(map).getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    private Mono<Void> message(ServerHttpResponse resp, String message) {

        return message(resp, message, HttpStatus.UNAUTHORIZED.value());
    }

    private Mono<Void> message(ServerWebExchange exchange, IErrorCode errorCode) {

        return message(exchange.getResponse(), errorCode.getMsg(), (int) errorCode.getCode());
    }

    /**
     * getOrder
     *
     * @return int
     */
    @Override
    public int getOrder() {
        return -100;
    }

    /**
     * JWT验证
     * @param token
     * @return claims
     */
    private Claims verifyJWT(String token){

        return JwtUtil.JWTDecode(token, jwtSecretKey);
    }

}