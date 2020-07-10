package com.loeyae.cloud.commons.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * RedisService
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @version 1.0
 * @date 2020/7/10 9:41
 */
public interface RedisService {

    /*
         --------------- key 相关 -----------------------------
     */

    /**
     * 删除
     *
     * @param key key
     */
    void delete(Object key);

    /**
     * 检查key是否存在
     */
    Boolean hasKey(Object key);

    /**
     * 批量删除key
     */
    void delete(Collection keys);

    /**
     * 设置key的过期时间
     */
    Boolean expire(String key, long timeout, TimeUnit unit);

    /**
     * 查询key的过期时间
     */
    Long getExpire(String key);

    /*
         --------------- String 相关 -----------------------------
     */

    /**
     * 设置字符
     *
     * @param seconds  有效时间 秒
     * @param timeUnit 时间单位
     */
    void setString(String key, String value, long seconds, TimeUnit timeUnit);

    /**
     * 批量设置字符
     */
    void multiSet(Map<String, String> maps);

    /**
     * 获取字符
     */
    String getString(String key);

    /**
     * 批量获取字符
     */
    List multiGet(Collection keys);

     /*
         --------------- Hash 相关 -----------------------------
     */

    /**
     * 设置哈希
     */
    void setHash(String key, String hashKey, String value);

    /**
     * 设置多个哈希
     */
    void setHashAll(String key, Map maps);

    /**
     * 获取哈希value值
     */
    <T> T getHashValue(String key, String field);

    /**
     * 获取指定字段的所有哈希值
     */
    <T> List<T> getHashValues(String key, Collection fields);

    /**
     * 获取整个哈希
     */
    <T, F> Map<T, F> getHashAll(String key);

    /**
     * 删除一个或多个哈希表字段
     */
    Long deleteHash(String key, Object... fields);

    /**
     * 查看哈希表key中指定的字段是否存在
     */
    boolean hasHashField(String key, String field);

    /*
         --------------- Bean 相关 -----------------------------
     */

    /**
     * 设置Bean
     */
    void setBean(String key, Object obj);

    void setBean(String key, Object obj, long seconds, TimeUnit timeUnit);

    /**
     * 获取Bean
     */
    <T> T getBean(String key);

    /*
         --------------- 原子性 相关 -----------------------------
     */

    /**
     * 取一个自增
     *
     * @param key key
     * @param value 自增
     */
    Long incrementLong(String key, Long value);

    /**
     * 取一个自减
     *
     * @param key key
     * @param value 自增
     */
    Long decrementLong(String key, Long value);


    /*
        --------------- @TODO List 栈 相关 -----------------------------
     */

    /*
        --------------- @TODO Set 相关 -----------------------------
     */

    /**
     * 集合中添加元素
     * @param key key
     * @param <T> 对象
     */
    <T> void addSet(String key, T... t);

    /**
     * 集合中添加元素
     * @param key key
     * @param seconds 秒
     * @param timeUnit 时间单位
     * @param <T> 对象
     */
    <T> void addSetAndTimeout(String key, long seconds, TimeUnit timeUnit, T... t);

    /**
     * 取得集合所有元素
     * @param key key
     * @param <T> T
     * @return
     */
    <T> Set<T> getSet(String key);
    /*
        --------------- @TODO ZSet 相关 -----------------------------
     */


}
