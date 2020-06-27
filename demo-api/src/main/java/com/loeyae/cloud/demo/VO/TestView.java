package com.loeyae.cloud.demo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Test View Object.
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020-06-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestView implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 钱包
     */
    private BigDecimal wallet;

    /**
     * 收入
     */
    private Double earning;

    /**
     * box
     */
    private Float box;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 描述
     */
    private String source;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;


}