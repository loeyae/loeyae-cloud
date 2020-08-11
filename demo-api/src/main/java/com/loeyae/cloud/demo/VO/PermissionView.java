package com.loeyae.cloud.demo.VO;

import lombok.Data;

/**
 * PermissionView.
 *
 * @date: 2020-08-11
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Data
public class PermissionView {
    private String code;
    private boolean allowed;
}