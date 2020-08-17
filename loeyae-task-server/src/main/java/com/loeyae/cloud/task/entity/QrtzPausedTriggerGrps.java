package com.loeyae.cloud.task.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @since 2020-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QrtzPausedTriggerGrps implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("SCHED_NAME")
    private String schedName;

    @TableField("TRIGGER_GROUP")
    private String triggerGroup;


}
