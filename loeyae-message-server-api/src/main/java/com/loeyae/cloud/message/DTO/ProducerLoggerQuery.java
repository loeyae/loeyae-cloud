package com.loeyae.cloud.message.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 生产者日志
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProducerLoggerQuery implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 自增ID
     */
    @PositiveOrZero
    private Long id;

    /**
     * producer ID
     */
    private Integer producer;

    /**
     * service name
     */
    @Size(max = 32)
    private String service;

    /**
     * action name
     */
    @Size(max = 32)
    private String action;

    /**
     * message
     */
    private String message;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;

}