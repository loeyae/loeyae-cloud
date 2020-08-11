package com.loeyae.cloud.demo.VO;

import lombok.Data;
import org.springframework.http.HttpMethod;

/**
 * MenuView.
 *
 * @date: 2020-08-11
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Data
public class MenuView {
    private String url;
    private String code;
    private String method = HttpMethod.GET.name();
}