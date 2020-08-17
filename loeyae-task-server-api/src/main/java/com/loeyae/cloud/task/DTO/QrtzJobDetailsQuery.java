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
public class QrtzJobDetailsQuery implements Serializable {
    private static final long serialVersionUID = 1L;


    @Length(max = 120)
    private String schedName;

    @Length(max = 190)
    private String jobName;

    @Length(max = 190)
    private String jobGroup;

    @Length(max = 250)
    private String description;

    @Length(max = 250)
    private String jobClassName;

    @Length(max = 1)
    private String isDurable;

    @Length(max = 1)
    private String isNonconcurrent;

    @Length(max = 1)
    private String isUpdateData;

    @Length(max = 1)
    private String requestsRecovery;

    private Blob jobData;

}