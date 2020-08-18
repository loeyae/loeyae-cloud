package com.loeyae.cloud.commons.validation.constraints;


import com.loeyae.cloud.commons.validation.validator.EnumsContainsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * EnmusContains.
 *
 * @date: 2020-08-16
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {EnumsContainsValidator.class})
public @interface EnumsContains {

    boolean required() default true;

    String propertyName();

    Class<? extends Enum> enums();

    String message() default "{validation.constraints.EnumsContains.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        EnumsContains[] value();
    }

}
