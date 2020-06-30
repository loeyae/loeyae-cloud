package com.loeyae.cloud.demo.DTO;

import javax.validation.constraints.Digits;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * TestQuery
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/28 16:50
 */
public class TestQuery  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Size(max = 32)
    private String name;

    /**
     * 钱包
     */
    @PositiveOrZero
    @Digits(integer = 5, fraction = 2)
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
