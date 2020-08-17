package com.loeyae.cloud.task.VO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
public class QrtzCalendarsView implements Serializable {
    private static final long serialVersionUID = 1L;


    private String schedName;

    private String calendarName;

    private Blob calendar;

}