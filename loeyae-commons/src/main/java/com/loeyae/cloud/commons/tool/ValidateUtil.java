/*
 * Licensed under the Apache License, Version 2.0 (the "License"),
 * see LICENSE for more details: http://www.apache.org/licenses/LICENSE-2.0.
 */
package com.loeyae.cloud.commons.tool;

import com.loeyae.cloud.commons.exception.GlobalException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

/**
 *
 * @author Zhang Yi <loeyae@gmail.com>
 */
public class ValidateUtil {

    /**
     * 换行符.
     */
    private static String LINE_SEPARATOR = System.getProperty("line.separator");

    private static Validator validator;

    private ValidateUtil() {
        throw new IllegalStateException("Utility class");
    }

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws Exception    校验不通过，则报RRException异常
     */
    public static <T> void validateEntity(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            List<Object> jsonList = new ArrayList<>();
            for(ConstraintViolation<T> constraint:  constraintViolations){
                Map<String, Object> jsonObject = new HashMap<>(2);
                jsonObject.put("name", constraint.getPropertyPath().toString());
                jsonObject.put("msg", constraint.getMessage());
                jsonList.add(jsonObject);
            }

            throw new GlobalException(JsonTool.beanToJson(jsonList));
        }
    }

    /**
     * validateEntity
     *
     * @param object
     * @param cls
     * @param groups
     * @param <V>
     * @param <T>
     */
    public static <V, T> void validateEntity(T object, Class<V> cls, Class<?>... groups) {
        V entity = BeanUtils.copyToEntity(object, cls);
        validateEntity(entity, groups);
    }

    /**
     * validateParamter
     *
     * @param cls
     * @param propertName
     * @param value
     * @param groups
     * @param <T>
     */
    public static <T> void validateParamter(Class<T> cls, String propertName, Object value, Class<?>... groups) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validateValue(cls, propertName, value, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for(ConstraintViolation<T> constraint:  constraintViolations){
                msg.append(constraint.getPropertyPath())
                    .append("<br>")
                    .append(constraint.getLeafBean() == null ? constraint.getRootBeanClass().getName() : constraint.getLeafBean().getClass().getName())
                    .append("<br>")
                    .append(constraint.getRootBeanClass())
                    .append("<br>")
                    .append(constraint.getExecutableReturnValue())
                    .append("<br>")
                    .append(constraint.getMessage())
                    .append("<br>");
            }

            throw new GlobalException(msg.toString());
        }
    }
}
