package com.loeyae.cloud.message.VO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
public class ConsumerLoggerView implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 自增ID
     */
    private Long id;

    /**
     * 消费者ID
     */
    private Integer consumer;

    /**
     * 消费者服务名称
     */
    private String service;

    /**
     * 生产者服务名称
     */
    private String producer;

    /**
     * 生产者事件ID
     */
    private String action;

    private String message;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

}