package com.loeyae.cloud.demo.controller.BO;

import com.loeyae.cloud.commons.validation.Insert;
import com.loeyae.cloud.commons.validation.Primary;
import com.loeyae.cloud.commons.validation.Query;
import com.loeyae.cloud.commons.validation.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

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
    @NotNull(groups = {Primary.class})
    @NotEmpty(groups = {Primary.class, Query.class})
    private Integer id;

    /**
     * 名称
     */
    @NotNull(groups = {Insert.class})
    @NotBlank(groups = {Insert.class, Update.class, Query.class})
    @Size(min = 1, max = 32, groups = {Insert.class, Update.class, Query.class})
    private String name;

    /**
     * 钱包
     */
    @NotNull(groups = {Insert.class})
    @Positive(groups = {Insert.class, Update.class, Query.class})
    private BigDecimal wallet;

    /**
     * 收入
     */
    @NotNull(groups = {Insert.class})
    @Positive(groups = {Insert.class, Update.class, Query.class})
    private Double earning;

    /**
     * box
     */
    @NotNull(groups = {Insert.class})
    @Positive(groups = {Insert.class, Update.class, Query.class})
    private Float box;

    /**
     * 状态
     */
    @Positive(groups = {Insert.class, Update.class, Query.class})
    private Integer status;

    /**
     * 描述
     */
    @NotEmpty(groups = {Insert.class})
    @NotBlank(groups = {Insert.class, Update.class, Query.class})
    private String source;
}