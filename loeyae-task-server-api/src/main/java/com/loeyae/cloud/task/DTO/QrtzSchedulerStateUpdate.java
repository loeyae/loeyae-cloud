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
public class QrtzSchedulerStateUpdate implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(required = false)
    @Length(max = 120)
    private String schedName;

    @NotBlank(required = false)
    @Length(max = 190)
    private String instanceName;

    private Long lastCheckinTime;

    private Long checkinInterval;

}