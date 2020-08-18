package com.loeyae.cloud.task.VO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
public class JobExecuteLogView implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    private Long id;

    /**
     * 任务ID
     */
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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}