package com.loeyae.cloud.task.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import com.loeyae.cloud.commons.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrtzSimpropTriggersCreate implements Serializable {
    private static final long serialVersionUID = 1L;



    @NotNull
    @NotBlank
    @Length(max = 120)
    private String schedName;


    @NotNull
    @NotBlank
    @Length(max = 190)
    private String triggerName;


    @NotNull
    @NotBlank
    @Length(max = 190)
    private String triggerGroup;


    @Length(max = 512)
    private String strProp1;


    @Length(max = 512)
    private String strProp2;


    @Length(max = 512)
    private String strProp3;


    private Integer intProp1;


    private Integer intProp2;


    private Long longProp1;


    private Long longProp2;


    @Digits(integer = 9, fraction = 4)
    private BigDecimal decProp1;


    @Digits(integer = 9, fraction = 4)
    private BigDecimal decProp2;


    @Length(max = 1)
    private String boolProp1;


    @Length(max = 1)
    private String boolProp2;

}