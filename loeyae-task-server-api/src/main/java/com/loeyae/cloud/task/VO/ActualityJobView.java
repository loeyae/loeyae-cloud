package com.loeyae.cloud.task.VO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 真实任务
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualityJobView implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    private Integer id;

    /**
     * 状态，0：初始化，1：激活，2：暂停，3：取消
     */
    private Integer status;

    private String service;

    private String url;

    private String method;

    private String parameter;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}