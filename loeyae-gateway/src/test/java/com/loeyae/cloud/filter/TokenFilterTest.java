package com.loeyae.cloud.filter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020/6/7 21:39
 */
@ExtendWith(SpringExtension.class)
class TokenFilterTest {

    @Test
    void testFilter()
    {
        PathMatcher matcher = new AntPathMatcher();
        assertTrue(matcher.match("/**/login", "/aa/b-b/login"));
        assertTrue(matcher.match("/**/get/token", "/v1/dotnet-demo/get/token"));
    }
}