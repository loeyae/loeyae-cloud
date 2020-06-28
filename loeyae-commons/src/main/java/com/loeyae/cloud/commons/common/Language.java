package com.loeyae.cloud.commons.common;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * Support Language
 *
 * @author ZhangYi<loeyae@gmail.com>
 * @version 1.0
 * @date 2020/6/28 10:21
 */
@Getter
public enum Language {

    /**
     * 英文
     */
    EN_US("en_US"),
    /**
     * 简体中文
     */
    ZH_CN("zh_CN");

    /**
     * 语言
     */
    private String lang;

    Language(String lang)
    {
        this.lang = lang;
    }

    /**
     * 获取指定语言类型(如果没有对应的语言类型,则返回中文)
     *
     * @param lang 语言类型
     * @return String
     */
    public static String getLanguageType(String lang){
        if (StringUtils.isEmpty(lang)) {
            return ZH_CN.lang;
        }
        for (Language language : Language.values()) {
            if (language.lang.equalsIgnoreCase(lang)) {
                return language.lang;
            }
        }
        return ZH_CN.lang;
    }
}
