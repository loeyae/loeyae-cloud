/*
 * Licensed under the Apache License, Version 2.0 (the "License"),
 * see LICENSE for more details: http://www.apache.org/licenses/LICENSE-2.0.
 */
package com.loeyae.cloud.commons.tool;

import com.loeyae.cloud.commons.exception.BaseErrorCode;
import com.loeyae.cloud.commons.exception.GlobalException;
import com.loeyae.cloud.commons.exception.ValidateException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

/**
 *
 * @author Zhang Yi <loeyae@gmail.com>
 */
public class ValidateUtil {

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
        buildMessage(constraintViolations);
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
        buildMessage(constraintViolations);
    }

    public static <T> void buildMessage(Set<ConstraintViolation<T>> constraintViolations)
    {
        if (!constraintViolations.isEmpty()) {
            List<Object> jsonList = new ArrayList<>();
            for(ConstraintViolation<T> constraint:  constraintViolations){
                Map<String, Object> jsonObject = new HashMap<>(2);
                jsonObject.put("name", constraint.getPropertyPath().toString());
                jsonObject.put("msg", constraint.getMessage());
                jsonList.add(jsonObject);
            }

            throw new ValidateException(JsonTool.beanToJson(jsonList));
        }
    }
}
