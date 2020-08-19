package com.loeyae.cloud.task.DTO;

import com.loeyae.cloud.commons.validation.constraints.EnumsContains;
import com.loeyae.cloud.task.enums.JobStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;
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
public class ActualityJobQuery implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    @PositiveOrZero
    private Integer id;

    /**
     * 状态，0：初始化，1：激活，2：暂停，3：取消
     */
    @EnumsContains(enums = JobStatus.class, propertyName = "code")
    private Integer status;

    @Length(max = 64)
    private String service;

    @Length(max = 10)
    private String method;

}