package com.loeyae.cloud.commons.validation.validator;

import com.loeyae.cloud.commons.validation.constraints.NotBlank;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * 不能空白串验证器.
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020-07-12
 */
public class NotBlankValidator implements ConstraintValidator<NotBlank, String> {

    /**
     * required
     */
    private boolean required = true;

    /**
     * initialize
     * @param constraintAnnotation
     */
    @Override
    public void initialize(NotBlank constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    /**
     * isValid
     *
     * @param s
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return execute(s, constraintValidatorContext);
        } else {
            if (Objects.isNull(s)) {
                return true;
            }
            return execute(s, constraintValidatorContext);
        }
    }

    /**
     * execute
     *
     * @param s
     * @param constraintValidatorContext
     * @return
     */
    private boolean execute(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        if (Objects.isNull(s)) {
            return false;
        }
        String trimString = s.trim();
        if(trimString.length() <1) {
            return false;
        }
        return true;
    }

}