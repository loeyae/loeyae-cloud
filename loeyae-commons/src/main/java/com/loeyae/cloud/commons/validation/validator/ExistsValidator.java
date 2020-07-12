package com.loeyae.cloud.commons.validation.validator;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loeyae.cloud.commons.tool.SpringContextTool;
import com.loeyae.cloud.commons.validation.constraints.Exists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.Serializable;
import java.util.Objects;

/**
 * ExistsValidator.
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020-07-12
 */
public class ExistsValidator implements ConstraintValidator<Exists, Serializable> {

    private boolean required = true;

    private Class<? extends IService> servcice = null;

    @Override
    public void initialize(Exists constraintAnnotation) {
        required = constraintAnnotation.required();
        servcice = constraintAnnotation.service();
    }

    @Override
    public boolean isValid(Serializable o, ConstraintValidatorContext constraintValidatorContext) {
        if (!required && Objects.isNull(o)) {
            return true;
        }
        if (Objects.isNull(servcice) || Objects.isNull(o)) {
            return false;
        }
        IService serviceImpl = SpringContextTool.getBean(servcice);
        Object entity = serviceImpl.getById(o);
        if (Objects.isNull(entity)) {
            return false;
        }
        return true;
    }
}