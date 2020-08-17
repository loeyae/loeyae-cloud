package com.loeyae.cloud.task.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.loeyae.cloud.commons.validation.constraints.NotBlank;
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
public class QrtzJobDetailsUpdate implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(required = false)
    @Length(max = 120)
    private String schedName;

    @NotBlank(required = false)
    @Length(max = 190)
    private String jobName;

    @NotBlank(required = false)
    @Length(max = 190)
    private String jobGroup;

    @NotBlank(required = false)
    @Length(max = 250)
    private String description;

    @NotBlank(required = false)
    @Length(max = 250)
    private String jobClassName;

    @NotBlank(required = false)
    @Length(max = 1)
    private String isDurable;

    @NotBlank(required = false)
    @Length(max = 1)
    private String isNonconcurrent;

    @NotBlank(required = false)
    @Length(max = 1)
    private String isUpdateData;

    @NotBlank(required = false)
    @Length(max = 1)
    private String requestsRecovery;

    private Blob jobData;

}