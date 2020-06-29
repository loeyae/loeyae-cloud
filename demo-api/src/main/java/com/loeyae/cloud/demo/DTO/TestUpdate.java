package com.loeyae.cloud.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Test Update DTO
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/28 16:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestUpdate implements Serializable {
    private static final long serialVersionUID = 1L;

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
}
