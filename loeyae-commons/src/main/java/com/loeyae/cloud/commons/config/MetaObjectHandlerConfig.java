package com.loeyae.cloud.commons.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.format.datetime.joda.LocalDateParser;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * MetaObjectHandlerConfig
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/28 16:35
 */
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("modifyTime", LocalDateTime.now(), metaObject);
    }
}
