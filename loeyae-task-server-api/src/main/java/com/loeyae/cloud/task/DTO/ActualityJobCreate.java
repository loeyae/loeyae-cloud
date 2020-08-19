package com.loeyae.cloud.task.DTO;

import com.loeyae.cloud.task.validation.constraints.ValidCronExpression;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import com.loeyae.cloud.commons.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.hibernate.validator.constraints.ScriptAssert;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

/**
 * <p>
 * 真实任务
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualityJobCreate implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Length(max = 64)
    private String service;


    @NotBlank
    @Length(max = 255)
    private String url;


    @NotBlank
    @Length(max = 10)
    private String method;


    @NotBlank(required = false)
    private String parameter;

    @ValidCronExpression(message = "无效的cron表达式，请参考：https://www.w3cschool.cn/quartz_doc/quartz_doc-lwuv2d2a.html")
    private String cron;

}