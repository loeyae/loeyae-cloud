package com.loeyae.cloud.message.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 生产者
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProducerQuery implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 自增ID
     */
    @PositiveOrZero
    private Integer id;

    /**
     * 服务名称
     */
    @Size(max = 32)
    private String service;

    /**
     * 事件名称
     */
    @Size(max = 32)
    private String action;

    /**
     * 消息体结构
     */
    private String definition;

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