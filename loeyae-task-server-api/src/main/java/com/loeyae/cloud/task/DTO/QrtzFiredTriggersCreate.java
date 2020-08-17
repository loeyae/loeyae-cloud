package com.loeyae.cloud.task.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import com.loeyae.cloud.commons.validation.constraints.NotBlank;
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
public class QrtzFiredTriggersCreate implements Serializable {
    private static final long serialVersionUID = 1L;



    @NotNull
    @NotBlank
    @Length(max = 120)
    private String schedName;


    @NotNull
    @NotBlank
    @Length(max = 95)
    private String entryId;


    @NotNull
    @NotBlank
    @Length(max = 190)
    private String triggerName;


    @NotNull
    @NotBlank
    @Length(max = 190)
    private String triggerGroup;


    @NotNull
    @NotBlank
    @Length(max = 190)
    private String instanceName;


    @NotNull
    private Long firedTime;


    @NotNull
    private Long schedTime;


    @NotNull
    private Integer priority;


    @NotNull
    @NotBlank
    @Length(max = 16)
    private String state;


    @Length(max = 190)
    private String jobName;


    @Length(max = 190)
    private String jobGroup;


    @Length(max = 1)
    private String isNonconcurrent;


    @Length(max = 1)
    private String requestsRecovery;

}