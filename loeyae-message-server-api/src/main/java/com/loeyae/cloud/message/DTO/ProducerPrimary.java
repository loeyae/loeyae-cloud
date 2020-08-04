package com.loeyae.cloud.message.DTO;

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
public class ProducerPrimary implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 自增ID
     */
    @NotNull
    @PositiveOrZero
    private Integer id;

}