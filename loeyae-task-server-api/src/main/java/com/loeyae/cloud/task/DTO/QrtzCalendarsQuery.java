package com.loeyae.cloud.task.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import java.sql.Blob;
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
public class QrtzCalendarsQuery implements Serializable {
    private static final long serialVersionUID = 1L;


    @Length(max = 120)
    private String schedName;

    @Length(max = 190)
    private String calendarName;

    private Blob calendar;

}