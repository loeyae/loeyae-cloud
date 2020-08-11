package com.loeyae.cloud.feign;

import lombok.Data;

/**
 * Permission.
 *
 * @date: 2020-08-11
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Data
public class Permission {

    private String code;

    private Boolean allowed;

}