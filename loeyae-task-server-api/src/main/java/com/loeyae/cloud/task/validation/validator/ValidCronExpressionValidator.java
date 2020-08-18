package com.loeyae.cloud.task.validation.validator;

import com.loeyae.cloud.task.validation.constraints.ValidCronExpression;
import org.apache.commons.lang3.ObjectUtils;
import org.quartz.CronExpression;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * ValidCronExpressionValidator.
 *
 * @date: 2020-08-18
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
public class ValidCronExpressionValidator implements ConstraintValidator<ValidCronExpression, String> {

    boolean required;

    @Override
    public void initialize(ValidCronExpression constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String o, ConstraintValidatorContext constraintValidatorContext) {
        if (!required && Objects.isNull(o)) {
            return true;
        }
        if (ObjectUtils.isEmpty(o)) {
            return false;
        }
        return CronExpression.isValidExpression(o);
    }
}
