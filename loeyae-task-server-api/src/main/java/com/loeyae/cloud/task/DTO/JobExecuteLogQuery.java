package com.loeyae.cloud.task.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 任务执行log
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobExecuteLogQuery implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    @PositiveOrZero
    private Long id;

    /**
     * 任务ID
     */
    @PositiveOrZero
    private Integer jobId;

    /**
     * 是否执行成功
     */
    private Integer success;

    /**
     * 重试次数
     */
    private Integer retries;

    /**
     * 源log id
     */
    private Long original;

}