package com.loeyae.cloud.task.VO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
public class QrtzLocksView implements Serializable {
    private static final long serialVersionUID = 1L;


    private String schedName;

    private String lockName;

}