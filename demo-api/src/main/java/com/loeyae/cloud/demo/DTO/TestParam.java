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
public class TestParam implements Serializable {
    private static final long serialVersionUID = 1L;

   /**
     * ID
     */
    @Null(groups = {Insert.class, Update.class})
    @NotNull(groups = {Primary.class, Query.class})
    private Integer id;

    /**
     * 名称
     */
    @NotNull
    @Size(min = 1, max = 32)
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