package com.loeyae.cloud.task.VO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
public class QrtzSimpropTriggersView implements Serializable {
    private static final long serialVersionUID = 1L;


    private String schedName;

    private String triggerName;

    private String triggerGroup;

    private String strProp1;

    private String strProp2;

    private String strProp3;

    private Integer intProp1;

    private Integer intProp2;

    private Long longProp1;

    private Long longProp2;

    private BigDecimal decProp1;

    private BigDecimal decProp2;

    private String boolProp1;

    private String boolProp2;

}