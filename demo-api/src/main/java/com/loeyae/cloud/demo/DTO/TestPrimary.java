package com.loeyae.cloud.demo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
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
public class TestPrimary implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    @NotNull
    @PositiveOrZero
    private Integer id;

}