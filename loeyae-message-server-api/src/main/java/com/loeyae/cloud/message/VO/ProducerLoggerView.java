package com.loeyae.cloud.message.VO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
public class ProducerLoggerView implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 自增ID
     */
    private Long id;

    /**
     * producer ID
     */
    private Integer producer;

    /**
     * service name
     */
    private String service;

    /**
     * action name
     */
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