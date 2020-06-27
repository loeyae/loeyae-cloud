package com.loeyae.cloud.commons.tool;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * QueryWrapper工具类.
 *
 * @author: Zhang Yi <loeyae@gmail.com>
 */
public class QueryWapperUtils {

    private static Logger logger;

    private QueryWapperUtils() {
        throw new IllegalStateException("Utility class");
    }

    static {
        logger = LoggerFactory.getLogger(QueryWapperUtils.class);
    }

    public static QueryWrapper queryToWrapper(Object source, Class targetClass) {
        Class<?> actualEditable = source.getClass();
        PropertyDescriptor[] sourcePds = BeanUtils.getPropertyDescriptors(actualEditable);
        QueryWrapper queryWrapper = new QueryWrapper();
        for(PropertyDescriptor sourcePd: sourcePds) {
            if (sourcePd != null && sourcePd.getReadMethod() != null) {
                try {
                    Method readMethod = sourcePd.getReadMethod();
                    if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                        readMethod.setAccessible(true);
                    }

                    Object value = readMethod.invoke(source);
                    if (value == null) {
                        logger.debug("value is null");
                        continue;
                    }
                    Class type = sourcePd.getPropertyType();
                    String property  = sourcePd.getName();
                    String nProperty = property;
                    String m = "eq";
                    int index = property.indexOf("_");
                    if (index > -1) {
                        nProperty = property.substring(0, index);
                        m = property.substring(index+1);
                    }
                    if (String.class.equals(type)) {
                        PropertyDescriptor targetPd =
                                BeanUtils.getPropertyDescriptor(targetClass, nProperty);
                        Method targetReadMethod = targetPd.getReadMethod();
                        Class targetType = targetReadMethod.getReturnType();
                        if (targetType.isEnum()) {
                            Enum e = Enum.valueOf(targetType, value.toString());
                            Method em = targetType.getMethod("getCode");
                            value = em.invoke(e);
                        }
                    }
                    String column = null;
                    Class clazz = targetClass;
                    while (clazz != null) {
                        try {
                            Field field = clazz.getDeclaredField(nProperty);
                            TableField tableField = field.getAnnotation(TableField.class);
                            column = tableField.value();
                            break;
                        } catch (NoSuchFieldException e) {
                            clazz = clazz.getSuperclass();
                            if (Object.class.equals(clazz)) {
                                clazz = null;
                                break;
                            }
                        } catch (RuntimeException e) {
                            column = nProperty;
                            break;
                        }
                    }
                    if (clazz == null && column == null) {
                        continue;
                    }
                    Method method = QueryWrapper.class.getMethod(m, Object.class, Object.class);
                    method.invoke(queryWrapper, new Object[]{column, value});
                } catch (IllegalAccessException | NoSuchMethodException exc) {
                    throw new FatalBeanException("Could not copy properties from source to " +
                            "target", exc);
                } catch (InvocationTargetException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return queryWrapper;
    }

}