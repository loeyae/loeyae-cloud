package com.loeyae.cloud.commons.validation.validator;

import com.loeyae.cloud.commons.validation.constraints.EnumsContains;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * EnumsContainsValidator.
 *
 * @date: 2020-08-16
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
public class EnumsContainsValidator implements ConstraintValidator<EnumsContains, Object> {

    private static final String ENUMS_NAME = "name";
    private boolean required = true;

    private String propertyName;

    private Class<? extends Enum> enums;

    private Class<?> valueType;

    @Override
    public void initialize(EnumsContains constraintAnnotation) {
        required = constraintAnnotation.required();
        propertyName = constraintAnnotation.propertyName();
        enums = constraintAnnotation.enums();
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (!required && Objects.isNull(object)) {
            return true;
        }
        if (Objects.isNull(enums) || Objects.isNull(object)) {
            return false;
        }
        if (ENUMS_NAME.equals(propertyName)) {
            for (Enum en : enums.getEnumConstants()) {
                if (en.name().equals(object.toString())){
                    return true;
                }
            }
        } else {
            Method reader = enums.getMethod("get"+ StringUtils.capitalize(propertyName));
            if (ObjectUtils.isNotEmpty(reader)) {
                for (Enum en : enums.getEnumConstants()) {
                    Object val = reader.invoke(en);
                    if (object.equals(val)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}