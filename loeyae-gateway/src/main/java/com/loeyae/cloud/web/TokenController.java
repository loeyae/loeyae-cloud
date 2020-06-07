package com.loeyae.cloud.web;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.filter.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * TokenController
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/7 16:24
 */
@RestController
public class TokenController {

    @Value("${loeyae.jwtSecretKey}")
    private String jwtSecretKey;

    @RequestMapping(value = "/get/token")
    public ApiResult get() {
        String token = JwtUtil.JWTEncode(new HashMap<String, Object>(){{
            put("id", 0);
            put("name","test");
        }}, jwtSecretKey, "testToken", "bys.cd", "bys.cd token", 3600000);
        return ApiResult.ok(new HashMap<String, String>(){{
            put("token", token);
        }});
    }
}
