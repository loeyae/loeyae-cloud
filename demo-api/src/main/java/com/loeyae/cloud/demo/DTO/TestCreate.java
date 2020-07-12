package com.loeyae.cloud.demo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-06-30
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
    @Length(max = 32)
    private String name;


    /**
     * 钱包
     */
    @PositiveOrZero
    @Digits(integer = 8, fraction = 2)
    private BigDecimal wallet;


    /**
     * 收入
     */
    @PositiveOrZero
    @Digits(integer = 8, fraction = 2)
    private Double earning;


    /**
     * box
     */
    @Digits(integer = 3, fraction = 2)
    private Float box;


    /**
     * 状态
     */
    private Integer status;


    /**
     * 描述
     */
    @NotNull
    @NotBlank
    private String source;



}