package com.loeyae.cloud.commons.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * RedisServiceImpl
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @version 1.0
 * @date 2020/7/10 9:41
 */
public class RedisServiceImpl implements RedisService{

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void delete(Object key) {
        redisTemplate.delete(key);
    }

    @Override
    public Boolean hasKey(Object key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void delete(Collection keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public void setString(String key, String value, long seconds, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, seconds, timeUnit);
    }

    @Override
    public void multiSet(Map<String, String> maps) {
        stringRedisTemplate.opsForValue().multiSet(maps);
    }

    @Override
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public List multiGet(Collection keys) {
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public void setHash(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }
    
    @Override
    public void setHashAll(String key, Map maps) {
        redisTemplate.opsForHash().putAll(key, maps);
    }

    @Override
    public <T> T getHashValue(String key, String field) {
        Object value =   redisTemplate.opsForHash().get(key, field);
        if (value == null) {
            return null;
        }
        return (T)value;
    }

    @Override
    public <T> List<T> getHashValues(String key, Collection fields) {
        return redisTemplate.opsForHash().multiGet(key, fields);
    }

    @Override
    public <T,F> Map<T,F>  getHashAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public Long deleteHash(String key, Object... fields) {
        return redisTemplate.opsForHash().delete(key, fields);
    }

    @Override
    public boolean hasHashField(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    @Override
    public void setBean(String key, Object obj) {
        redisTemplate.opsForValue().set(key, obj);
    }

    @Override
    public void setBean(String key, Object obj, long seconds, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, obj, seconds, timeUnit);
    }

    @Override
    public <T> T getBean(String key) {
        Object result = redisTemplate.opsForValue().get(key);
        if (result == null) {
            return null;
        }
        return (T)result;
    }

    @Override
    public Long incrementLong(String key, Long value) {
        return redisTemplate.opsForValue().increment(key, value);
    }

    @Override
    public Long decrementLong(String key, Long value) {
        return redisTemplate.opsForValue().decrement(key, value);
    }

    @Override
    public <T> void addSet(String key, T... t) {
        redisTemplate.opsForSet().add(key, t);
    }

    @Override
    public <T> void addSetAndTimeout(String key, long seconds, TimeUnit timeUnit, T... t) {
        redisTemplate.opsForSet().add(key, t);
        redisTemplate.expire(key, seconds, timeUnit);
    }

    @Override
    public <T> Set<T> getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

}
