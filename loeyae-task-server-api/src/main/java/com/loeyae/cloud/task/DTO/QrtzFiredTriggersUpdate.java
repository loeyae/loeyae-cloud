package com.loeyae.cloud.task.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.loeyae.cloud.commons.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrtzFiredTriggersUpdate implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(required = false)
    @Length(max = 120)
    private String schedName;

    @NotBlank(required = false)
    @Length(max = 95)
    private String entryId;

    @NotBlank(required = false)
    @Length(max = 190)
    private String triggerName;

    @NotBlank(required = false)
    @Length(max = 190)
    private String triggerGroup;

    @NotBlank(required = false)
    @Length(max = 190)
    private String instanceName;

    private Long firedTime;

    private Long schedTime;

    private Integer priority;

    @NotBlank(required = false)
    @Length(max = 16)
    private String state;

    @NotBlank(required = false)
    @Length(max = 190)
    private String jobName;

    @NotBlank(required = false)
    @Length(max = 190)
    private String jobGroup;

    @NotBlank(required = false)
    @Length(max = 1)
    private String isNonconcurrent;

    @NotBlank(required = false)
    @Length(max = 1)
    private String requestsRecovery;

}