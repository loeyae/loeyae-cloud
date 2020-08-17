package com.loeyae.cloud.task.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import com.loeyae.cloud.commons.validation.constraints.NotBlank;
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
public class QrtzTriggersCreate implements Serializable {
    private static final long serialVersionUID = 1L;



    @NotNull
    @NotBlank
    @Length(max = 120)
    private String schedName;


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
    private String jobName;


    @NotNull
    @NotBlank
    @Length(max = 190)
    private String jobGroup;


    @Length(max = 250)
    private String description;


    private Long nextFireTime;


    private Long prevFireTime;


    private Integer priority;


    @NotNull
    @NotBlank
    @Length(max = 16)
    private String triggerState;


    @NotNull
    @NotBlank
    @Length(max = 8)
    private String triggerType;


    @NotNull
    private Long startTime;


    private Long endTime;


    @Length(max = 190)
    private String calendarName;


    private Integer misfireInstr;


    private Blob jobData;

}