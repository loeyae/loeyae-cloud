package com.loeyae.cloud.filter;

import com.loeyae.cloud.commons.exception.BaseErrorCode;
import com.loeyae.cloud.commons.exception.IErrorCode;
import com.loeyae.cloud.commons.tool.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.DigestUtils;
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

    public static final String JWT_AUTH_KEY = "Authorization";
    public static final String REDIRECT_HEADER_PREFIX = "gw_re_";
    public static final String NULL_USER = "null";
    public static final String PERMISSION_FILTER_APP = "loeyeGatewayPermissionFilterApp";
    public static final String PERMISSION_FILTER_USER = "loeyeGatewayPermissionFilterUser";
    public static final String PERMISSION_ROLE_IS_ADMIN = "loeyeGatewayPermissionRoleIsAdmin";

    Logger logger= LoggerFactory.getLogger( TokenFilter.class );

    private final String[] verifyTokenUrls;

    private final String[] skipTokenUrls;

    private final List<String> skipExcludeUrls;

    private String jwtSecretKey;

    private final String isAdminKey;
    private final String identifiedId;
    private final String applicationKey;

    public TokenFilter(String[] verifyTokenUrls, String[] skipTokenUrls,
                       String[] skipExcludeUrls, String jwtSecretKey,
                       String isAdminKey, String identifiedId, String applicationKey) {
        this.verifyTokenUrls = verifyTokenUrls;
        this.skipTokenUrls = skipTokenUrls;
        this.skipExcludeUrls = Arrays.asList(skipExcludeUrls);
        this.jwtSecretKey = jwtSecretKey;
        this.isAdminKey = isAdminKey;
        this.identifiedId = identifiedId;
        this.applicationKey = applicationKey;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        log.info("Got url: {}", url);
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
                if (skipExcludeUrls.contains(url)) {
                    continue;
                }
                exchange.getAttributes().put(PERMISSION_FILTER_USER, NULL_USER);
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
            String userId = null;
            boolean isAdmin = false;
            for(Map.Entry<String, Object> entry : claims.entrySet()) {
                if (defaultKeys.contains(entry.getKey())) {
                    continue;
                }
                headers.set(REDIRECT_HEADER_PREFIX + entry.getKey(),
                        String.valueOf(entry.getValue()));
                if (entry.getKey().equals(identifiedId)) {
                    userId = String.valueOf(entry.getValue());
                }
                if (entry.getKey().equals(isAdminKey)) {
                    isAdmin = (boolean)entry.getValue();
                }
            }

            List<String> appHeader = headers.get(applicationKey);
            String app = "default";
            if (ObjectUtils.isNotEmpty(appHeader)) {
                app = appHeader.get(0);
            }
            if (userId == null) {
                userId = DigestUtils.md5DigestAsHex(token.getBytes());
            }

            exchange.getAttributes().put(PERMISSION_FILTER_APP, app);
            exchange.getAttributes().put(PERMISSION_FILTER_USER, userId);
            exchange.getAttributes().put(PERMISSION_ROLE_IS_ADMIN, userId);
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
    public static Mono<Void> message(ServerHttpResponse resp, String message, int code) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", message);
        DataBuffer buffer = resp.bufferFactory().wrap(toJSONString(map).getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    public static  Mono<Void> message(ServerHttpResponse resp, String message) {

        return message(resp, message, HttpStatus.UNAUTHORIZED.value());
    }

    public static  Mono<Void> message(ServerWebExchange exchange, IErrorCode errorCode) {

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
