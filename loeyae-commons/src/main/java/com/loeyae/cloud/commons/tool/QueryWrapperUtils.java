package com.loeyae.cloud.commons.tool;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * QueryWrapper工具类.
 *
 * @author: Zhang Yi <loeyae@gmail.com>
 */
public class QueryWrapperUtils {

    public static final Integer DEFAULT_PAGE = 1;
    public static final Integer DEFAULT_SIZE = 10;
    public static final String DEFAULT_SORT_COLUMN = "id";
    public static final Integer SORT_BY_ASC = 1;
    public static final Integer SORT_BY_DESC = -1;
    private static Logger logger;

    public static final String PAGE = "p";

    public static final String SIZE = "s";

    public static final String ORDER = "o";

    public static final String COLUMN = "c";

    private QueryWrapperUtils() {
        throw new IllegalStateException("Utility class");
    }

    static {
        logger = LoggerFactory.getLogger(QueryWrapperUtils.class);
    }

    /**
     * ofPage
     *
     * @param source
     * @param <T>
     * @return
     */
    public static <T> IPage<T> ofPage(Object source)
    {

        Integer page = DEFAULT_PAGE;
        Integer size = DEFAULT_SIZE;
        Object p = getPropertyValue(source, PAGE, true);
        if (p != null) {
            page = Integer.valueOf(p.toString());
        }
        Object s = getPropertyValue(source, SIZE, true);
        if (s != null) {
            size = Integer.valueOf(s.toString());
        }
        Page<T> tPage = new Page<>();
        tPage.setCurrent(page);
        tPage.setSize(size);
        List<OrderItem> orderItems = ofOrders(source);
        tPage.addOrder(orderItems);
        return tPage;
    }

    /**
     * ofOrders
     *
     * @param source
     * @return
     */
    public static List<OrderItem> ofOrders(Object source)
    {
        String column = DEFAULT_SORT_COLUMN;
        Integer order = SORT_BY_DESC;
        Object o = getPropertyValue(source, ORDER, true);
        if (!Objects.isNull(o)) {
            order = Integer.valueOf(o.toString());
        }
        Object c = getPropertyValue(source, COLUMN, true);
        if (!Objects.isNull(c)) {
            column = c.toString();
        }
        OrderItem orderItem = SORT_BY_ASC.equals(order) ? OrderItem.asc(column) : OrderItem.desc(column);
        return new ArrayList<OrderItem>(){{
            add(orderItem);
        }};
    }

    public static <T> QueryWrapper<T> queryToWrapper(Object source, Class<T> targetClass) {
        Class<?> actualEditable = source.getClass();
        PropertyDescriptor[] sourcePds = BeanUtils.getPropertyDescriptors(actualEditable);
        QueryWrapper<T> queryWrapper = new QueryWrapper();
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


    /**
     * getPropertyValue
     *
     * @param source
     * @param propertyName
     * @return
     */
    public static Object getPropertyValue(Object source, String propertyName, Boolean reset)
    {
        try {
            PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(source.getClass(), propertyName);
            if (!Objects.isNull(propertyDescriptor)) {
                Method readMethod = propertyDescriptor.getReadMethod();
                if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                    readMethod.setAccessible(true);
                }
                Object value = readMethod.invoke(source);
                if (Boolean.FALSE.equals(reset)) {
                    return value;
                }
                Method writeMethod = propertyDescriptor.getWriteMethod();
                if (Objects.isNull(writeMethod)) {
                    return value;
                }
                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                    writeMethod.setAccessible(true);
                }
                writeMethod.invoke(source, value);
                return value;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


}