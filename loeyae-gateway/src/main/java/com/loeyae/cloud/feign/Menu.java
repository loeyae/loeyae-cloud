package com.loeyae.cloud.feign;

import lombok.Data;
import org.springframework.http.HttpMethod;

/**
 * Menu.
 *
 * @date: 2020-08-11
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Data
public class Menu {

    private String url;

    private String code;

    private String method = HttpMethod.GET.name();
}