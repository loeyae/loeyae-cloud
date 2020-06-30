package com.loeyae.cloud.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * TestPrimary.
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020-06-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestPrimary implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Positive
    private int id;
}