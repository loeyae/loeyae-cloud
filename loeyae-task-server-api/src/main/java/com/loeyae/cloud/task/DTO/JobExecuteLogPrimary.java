package com.loeyae.cloud.task.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
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
public class JobExecuteLogPrimary implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    @NotNull
    @PositiveOrZero
    private Long id;

}