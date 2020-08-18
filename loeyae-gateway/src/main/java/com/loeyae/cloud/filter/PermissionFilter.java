package com.loeyae.cloud.filter;

import com.loeyae.cloud.commons.exception.BaseErrorCode;
import com.loeyae.cloud.commons.redis.RedisService;
import com.loeyae.cloud.commons.tool.SpringContextTool;
import com.loeyae.cloud.feign.*;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * PermissionFilter.
 *
 * @date: 2020-08-11
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
public class PermissionFilter implements GlobalFilter, Ordered {

    private final boolean enabled;
    private final boolean cacheEnabled;
    private final String cachePrefix;
    private final int cacheExpire;
    private final boolean routed;
    private final boolean matchMethod;

    Logger logger= LoggerFactory.getLogger( TokenFilter.class );

    private PermissionFeignClient permissionFeignClient;

    private RedisService redisService;

    public PermissionFilter(boolean enabled, boolean cacheEnabled, String cachePrefix,
                            int cacheExpire, boolean routed, boolean matchMethod)
    {
        this.enabled = enabled;
        this.cacheEnabled = cacheEnabled;
        this.cachePrefix = cachePrefix;
        this.cacheExpire = cacheExpire;
        this.routed = routed;
        this.matchMethod = matchMethod;
        if (this.enabled) {
            this.permissionFeignClient = SpringContextTool.getBean(PermissionFeignClient.class);
        }
        if (this.cacheEnabled) {
            this.redisService = SpringContextTool.getBean(RedisService.class);
        }
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (Boolean.FALSE.equals(this.enabled)) {
            return chain.filter(exchange);
        }
        boolean isAdmin = exchange.getAttribute(TokenFilter.PERMISSION_ROLE_IS_ADMIN);
        String appId = exchange.getAttribute(TokenFilter.PERMISSION_FILTER_APP);
        String userId = exchange.getAttribute(TokenFilter.PERMISSION_FILTER_USER);
        if (isAdmin || ObjectUtils.isEmpty(userId)) {
            return chain.filter(exchange);
        }
        MenuCollection menus = new MenuCollection();
        PermissionCollection permissions = new PermissionCollection();
        if (Boolean.TRUE.equals(this.cacheEnabled)) {
            try {
                if (redisService == null) {
                    this.redisService = SpringContextTool.getBean(RedisService.class);
                }
                MenuCollection menuCollection =
                        this.redisService.getBean(getMenusCacheKey(appId));
                if (ObjectUtils.isNotEmpty(menuCollection)) {
                    menus.addAll(menuCollection.getMenus());
                }
                PermissionCollection permissionCollection =
                        this.redisService.getBean(getPermissionCacheKey(appId, userId));
                if (ObjectUtils.isNotEmpty(permissionCollection)) {
                    permissions.addAll(permissionCollection.getPermissions());
                }
            } catch (Exception e) {
                logger.error("Cache error", e);
            }
        }
        if (menus.isEmpty()) {
            List<Menu> menuList = this.permissionFeignClient.getMenuList(appId).getData();
            logger.info("Got menus: {}", menuList);
            if (ObjectUtils.isNotEmpty(menuList)) {
                menus.addAll(menuList);
                if (ObjectUtils.isNotEmpty(redisService)) {
                    redisService.setBean(getMenusCacheKey(appId), menus, cacheExpire,
                            TimeUnit.SECONDS);
                }
            }
        }
        if (permissions.isEmpty()) {
            List<Permission> permissionList =
                    this.permissionFeignClient.getPermissionList(appId, userId).getData();
            if (ObjectUtils.isNotEmpty(permissionList)) {
                permissions.addAll(permissionList);
                if (ObjectUtils.isNotEmpty(redisService)) {
                    redisService.setBean(getPermissionCacheKey(appId, userId), permissions,
                            cacheExpire, TimeUnit.SECONDS);
                }
            }
        }
        Iterator<Menu> iterator = menus.iterator();
        String url = exchange.getRequest().getPath().toString();
        String method = exchange.getRequest().getMethodValue();
        PathMatcher matcher = new AntPathMatcher();
        boolean allowed = false;
        while (iterator.hasNext()) {
            Menu menu = iterator.next();
            if (matcher.match(menu.getUrl(), url) && (!matchMethod || method.equalsIgnoreCase(menu.getMethod()))) {
                Permission permission = permissions.searchCode(menu.getCode());
                if (ObjectUtils.isNotEmpty(permission) && Boolean.TRUE.equals(permission.getAllowed())) {
                    allowed = true;
                    break;
                }
            }
        }
        if (!allowed) {
            return TokenFilter.message(exchange, BaseErrorCode.PERMISSION_DENIED);
        }
        return chain.filter(exchange);
    }

    /**
     * 放到route之后获取转发后的url
     *
     * @return
     */
    @Override
    public int getOrder() {
        return routed ? 10151 : -90;
    }

    /**
     * getMenusCacheKey
     *
     * @param appId
     * @return
     */
    private String getMenusCacheKey(String appId)
    {
        return String.format("%s%s_%s", cachePrefix, "menus", appId);
    }

    /**
     * getPermissionCacheKey
     *
     * @param userId
     * @return
     */
    private String getPermissionCacheKey(String appId, String userId)
    {
        return String.format("%s%s_%s:%s", cachePrefix, "pfs", appId, userId);
    }
}