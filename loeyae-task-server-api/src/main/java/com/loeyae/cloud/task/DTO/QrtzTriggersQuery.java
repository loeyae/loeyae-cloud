package com.loeyae.cloud.task.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import java.sql.Blob;
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
public class QrtzTriggersQuery implements Serializable {
    private static final long serialVersionUID = 1L;


    @Length(max = 120)
    private String schedName;

    @Length(max = 190)
    private String triggerName;

    @Length(max = 190)
    private String triggerGroup;

    @Length(max = 190)
    private String jobName;

    @Length(max = 190)
    private String jobGroup;

    @Length(max = 250)
    private String description;

    private Long nextFireTime;

    private Long prevFireTime;

    private Integer priority;

    @Length(max = 16)
    private String triggerState;

    @Length(max = 8)
    private String triggerType;

    private Long startTime;

    private Long endTime;

    @Length(max = 190)
    private String calendarName;

    private Integer misfireInstr;

    private Blob jobData;

}