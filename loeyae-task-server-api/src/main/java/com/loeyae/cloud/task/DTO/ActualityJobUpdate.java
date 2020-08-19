package com.loeyae.cloud.task.DTO;

import com.loeyae.cloud.commons.validation.constraints.NotBlank;
import com.loeyae.cloud.task.validation.constraints.ValidCronExpression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
public class ActualityJobUpdate implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(required = false)
    @Length(max = 255)
    private String url;

    @NotBlank(required = false)
    @Length(max = 10)
    private String method;

    @NotBlank(required = false)
    private String parameter;

    @ValidCronExpression(required = false)
    private String cron;

}