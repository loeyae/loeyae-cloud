package com.loeyae.cloud.demo.DTO;

import com.loeyae.cloud.commons.validation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.*;

/**
 * Test Param DTO.
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @version 1.0
 * @date 2020-06-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCreate implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @NotNull
    @NotBlank
    @Size(max=32)
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
    @PositiveOrZero
    private Double earning;

    /**
     * box
     */
    @PositiveOrZero
    private Float box;

    /**
     * 状态
     */
    @PositiveOrZero
    private Integer status;

    /**
     * 描述
     */
    @NotBlank
    private String source;
}