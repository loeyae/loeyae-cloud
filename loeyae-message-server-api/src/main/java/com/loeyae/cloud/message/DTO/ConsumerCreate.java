package com.loeyae.cloud.message.DTO;

import com.loeyae.cloud.commons.validation.constraints.Exists;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
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
public class ConsumerCreate implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 消费者服务名称
     */
    @NotNull
    @NotBlank
    @Size(max = 32)
    private String service;


    /**
     * 消费的生产者id
     */
    @NotNull
    private Integer target;


    /**
     * 状态
     */
    private Integer status;


    /**
     * 是否删除
     */
    private Integer deleted;



}