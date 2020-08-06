package com.loeyae.cloud.message.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 消费者日志
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerLoggerUpdate implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 消费者ID
     */
    private Integer consumer;

    /**
     * 消费者服务名称
     */
    @NotBlank
    @Size(max = 32)
    private String service;

    /**
     * 生产者服务名称
     */
    @NotBlank
    @Size(max = 32)
    private String producer;

    /**
     * 生产者事件ID
     */
    @NotBlank
    @Size(max = 32)
    private String action;

    @NotBlank
    private String message;

}