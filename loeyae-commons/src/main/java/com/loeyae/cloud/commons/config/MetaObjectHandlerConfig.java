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
        if (LocalDateTime.class.equals(metaObject.getGetterType("createTime"))) {
            setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        } else {
            setFieldValByName("createTime", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if(metaObject.hasSetter("updateTime")) {
            if (LocalDateTime.class.equals(metaObject.getGetterType("updateTime"))) {
                setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            } else {
                setFieldValByName("updateTime", new Date(), metaObject);
            }
        } else if (metaObject.hasSetter("modifyTime")) {
            if (LocalDateTime.class.equals(metaObject.getGetterType("modifyTime"))) {
                setFieldValByName("modifyTime", LocalDateTime.now(), metaObject);
            } else {
                setFieldValByName("modifyTime", new Date(), metaObject);
            }
        }
    }
}
