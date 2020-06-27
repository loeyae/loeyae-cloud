/*
 * Copyright (c) 2019. Beyondsoft Corporation.
 * All Rights Reserved.
 *
 * BEYONDSOFT CONFIDENTIALITY
 *
 * The information in this file is confidential and privileged from Beyondsoft Corporation,
 * which is intended only for use solely by Beyondsoft Corporation.
 * Disclosure, copying, distribution, or use of the contents of this file by persons other than Beyondsoft Corporation
 * is strictly prohibited and may violate applicable laws.
 */

package com.loeyae.cloud.commons.tool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean工具.
 *
 * @date: 2019-06-14
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Slf4j
public abstract class BeanUtils extends org.springframework.beans.BeanUtils {

    public static <T, F> F copyToEntity(T source, Class<F> target)
    {
        try {
            F entity = target.newInstance();
            if (source == null) {
                return entity;
            }
            copyProperties(source, entity);
            return entity;
        } catch (Exception e) {
            log.error("BeanUtils Error: ", e);
            return null;
        }
    }

    /**
     * 对象复制 Enum自动转化
     *
     * @param source
     * @param target
     * @throws BeansException BeansException
     */
    public static void copyProperties(Object source, Object target)
    {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds)
        {
            if (targetPd.getWriteMethod() != null)
            {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null)
                {
                    try
                    {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers()))
                        {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
                        if (value != null)
                        {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers()))
                            {
                                writeMethod.setAccessible(true);
                            }

                            //判断源为Enum 目标为字符串, 将Enum转化为字符串
                            if (readMethod.getReturnType().isEnum() && String.class.equals(targetPd.getReadMethod().getReturnType())) {
                                writeMethod.invoke(target, value.toString());
                            }
                            //判断目标为Enum 源为字符串，将字符串转化为对应的Enum
                            else if (targetPd.getReadMethod().getReturnType().isEnum() && String.class.equals(value.getClass())) {
                                Class clazz = Class.forName(targetPd.getReadMethod().getReturnType().getName());
                                writeMethod.invoke(target, Enum.valueOf(clazz, (String) value));
                            }
                            else {
                                writeMethod.invoke(target, value);
                            }
                        }
                    }
                    catch (Exception ex)
                    {
                        throw new FatalBeanException("Could not copy properties from source to target", ex);
                    }
                }
            }
        }
    }

    /**
     * 将一个集合中的对象拷贝到另一个集合中的对象。
     * 有相同属性的值会拷贝过去.
     *
     * @param source 源对象集合
     * @param target 目标对象类型.
     * @param <T> 源目标对象类型
     * @param <F> 目标对象类型
     * @return 目标对象集合
     */
    public static <T, F> List<F> copyObjListProperties(List<T> source, Class<F> target) {
        if (source == null) {
            return new ArrayList<F>();
        }
        return source.parallelStream().map(item -> copyToEntity(item, target)).collect(Collectors.toList());
    }

}