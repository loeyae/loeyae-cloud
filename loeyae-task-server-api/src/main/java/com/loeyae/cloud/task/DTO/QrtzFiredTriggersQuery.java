package com.loeyae.cloud.task.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
public class QrtzFiredTriggersQuery implements Serializable {
    private static final long serialVersionUID = 1L;


    @Length(max = 120)
    private String schedName;

    @Length(max = 95)
    private String entryId;

    @Length(max = 190)
    private String triggerName;

    @Length(max = 190)
    private String triggerGroup;

    @Length(max = 190)
    private String instanceName;

    private Long firedTime;

    private Long schedTime;

    private Integer priority;

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