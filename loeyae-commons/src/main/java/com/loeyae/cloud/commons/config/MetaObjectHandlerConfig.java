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
        if (metaObject.hasSetter("createTime")) {
            setFieldValByName("createTime", new Date(), metaObject);
        }
        if (metaObject.hasSetter("updateTime")) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
        if (metaObject.hasSetter("modifyTime")) {
            setFieldValByName("modifyTime", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("updateTime")) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
        if (metaObject.hasSetter("modifyTime")) {
            setFieldValByName("modifyTime", new Date(), metaObject);
        }
    }
}
