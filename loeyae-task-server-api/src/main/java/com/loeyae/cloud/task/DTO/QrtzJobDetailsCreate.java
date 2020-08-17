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
public class QrtzJobDetailsCreate implements Serializable {
    private static final long serialVersionUID = 1L;



    @NotNull
    @NotBlank
    @Length(max = 120)
    private String schedName;


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


    @NotNull
    @NotBlank
    @Length(max = 250)
    private String jobClassName;


    @NotNull
    @NotBlank
    @Length(max = 1)
    private String isDurable;


    @NotNull
    @NotBlank
    @Length(max = 1)
    private String isNonconcurrent;


    @NotNull
    @NotBlank
    @Length(max = 1)
    private String isUpdateData;


    @NotNull
    @NotBlank
    @Length(max = 1)
    private String requestsRecovery;


    private Blob jobData;

}