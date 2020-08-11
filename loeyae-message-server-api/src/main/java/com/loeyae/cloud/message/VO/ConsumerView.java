package com.loeyae.cloud.message.VO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 消费者
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerView implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 消费者服务名称
     */
    private String service;

    /**
     * 消费的生产者id
     */
    private Integer target;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;

}