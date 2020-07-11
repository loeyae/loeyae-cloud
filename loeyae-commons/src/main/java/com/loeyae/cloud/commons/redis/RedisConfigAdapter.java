package com.loeyae.cloud.commons.redis;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Objects;

/**
 * RedisConfigAdapter
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/7/10 9:41
 */

@EnableCaching
@Slf4j
public abstract class RedisConfigAdapter extends CachingConfigurerSupport {

    /**
     * 默认缓存天数
     */
    private final int defaultDays = 7;

    /**
     * 缓存前缀
     */
    protected abstract String getPrefix();

    private static final String SYMBOL1 = ":";
    protected static final String SYMBOL2 = "_";

    /**
     * 在使用@Cacheable时，如果不指定key，则使用个默认的key生成器生成的key
     * 设置默认key的策略：类名：方法名：参数
     *
     * @return KeyGenerator
     */
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(getPrefix());
                sb.append(SYMBOL1);
                sb.append(target.getClass().getName());
                sb.append(SYMBOL1);
                sb.append(method.getName());
                sb.append(SYMBOL1);
                StringBuilder paramsSb = new StringBuilder();
                for (Object param : params) {
                    // 如果不指定，默认生成包含到键值中
                    if (param != null) {
                        paramsSb.append(param.toString());
                    }
                }
                if (paramsSb.length() > 0) {
                    sb.append(SYMBOL2).append(paramsSb);
                }
                return sb.toString();
            }
        };
    }

    /**
     * 自定义CacheManager 设置注解缓存策略
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        // 全局redis缓存过期时间 30天 序列化方式:fastjson
        GenericFastJsonRedisSerializer fastJsonRedisSerializer;
        fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(defaultDays))
                .serializeValuesWith(pair);
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }

    /**
     * 配置redisTemplate的序列化方式
     *
     * @param connectionFactory RedisConnectionFactory
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        //使用fastjson序列化
        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        // value值的序列化采用fastJsonRedisSerializer
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        // key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.setConnectionFactory(connectionFactory);
        template.afterPropertiesSet();
        return template;
    }
}
