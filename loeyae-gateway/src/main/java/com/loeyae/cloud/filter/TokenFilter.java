package com.loeyae.cloud.filter;

import com.loeyae.cloud.commons.exception.BaseErrorCode;
import com.loeyae.cloud.commons.exception.IErrorCode;
import com.loeyae.cloud.commons.tool.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
public class TokenFilter implements GlobalFilter, Ordered {

    private static final String JWT_AUTH_KEY = "Authorization";
    private static final String REDIRECT_HEADER_PREFIX = "gw_re_";

    Logger logger= LoggerFactory.getLogger( TokenFilter.class );

    private final String[] verifyTokenUrls;

    private String[] skipTokenUrls;

    private String jwtSecretKey;

    public TokenFilter(String[] verifyTokenUrls, String[] skipTokenUrls, String jwtSecretKey) {
        this.verifyTokenUrls = verifyTokenUrls;
        this.skipTokenUrls = skipTokenUrls;
        this.jwtSecretKey = jwtSecretKey;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        PathMatcher matcher = new AntPathMatcher();
        boolean matched = false;
        for (String tokeUrl: verifyTokenUrls) {
            matched = matcher.match(tokeUrl, url);
            if (matched) {
                break;
            }
        }
        if (!matched) {
            return chain.filter(exchange);
        }
        for (String skipUrl: skipTokenUrls) {
            boolean result = matcher.match(skipUrl, url);
            if (result) {
                return chain.filter(exchange);
            }
        }

        //从请求头中取出token
        String token = exchange.getRequest().getHeaders().getFirst(JWT_AUTH_KEY);
        logger.warn("got token: [{}]", token);
        //未携带token或token在黑名单内
        if (token == null || token.isEmpty() || StringUtils.isBlank(token)) {
            return message(exchange, BaseErrorCode.TOKEN_INVALID);
        }
        try {
            Claims claims = verifyJWT(token);
            HttpHeaders headers = new HttpHeaders();
            headers.putAll(exchange.getRequest().getHeaders());
            List<String> defaultKeys = Arrays.asList(
                    Claims.AUDIENCE,
                    Claims.EXPIRATION,
                    Claims.ID,
                    Claims.ISSUED_AT,
                    Claims.ISSUER,
                    Claims.NOT_BEFORE,
                    Claims.SUBJECT
                );
            for(Map.Entry<String, Object> entry : claims.entrySet()) {
                if (defaultKeys.contains(entry.getKey())) {
                    continue;
                }
                headers.set(REDIRECT_HEADER_PREFIX + entry.getKey(),
                        String.valueOf(entry.getValue()));
            }
            ServerHttpRequestDecorator newRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                @Override
                public HttpHeaders getHeaders() {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.putAll(headers);
                    return httpHeaders;
                }
            };
            return chain.filter(exchange.mutate().request(newRequest).build());
        } catch (ExpiredJwtException expiredJwtException) {
            return message(exchange, BaseErrorCode.TOKEN_EXPIRE);
        } catch (Exception e) {
            logger.error("error: ", e);
            return message(exchange, BaseErrorCode.TOKEN_INVALID);
        }
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
