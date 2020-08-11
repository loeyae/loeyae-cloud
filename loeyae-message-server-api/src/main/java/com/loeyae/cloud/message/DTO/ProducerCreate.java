package com.loeyae.cloud.message.DTO;

import com.alibaba.fastjson.JSONObject;
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
public class ProducerCreate implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 服务名称
     */
    @NotNull
    @NotBlank
    @Size(max = 32)
    private String service;


    /**
     * 事件名称
     */
    @NotNull
    @NotBlank
    @Size(max = 32)
    private String action;


    /**
     * 消息体结构
     */
    @NotNull
    @NotEmpty
    private JSONObject definition;


    /**
     * 状态
     */
    private Integer status;


    /**
     * 是否删除
     */
    private Integer deleted;



}