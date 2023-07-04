package com.laolang.shop.modules.auth.util;

import cn.hutool.core.util.StrUtil;
import com.laolang.shop.modules.auth.consts.logic.AuthConsts;
import com.laolang.shop.modules.auth.properties.TokenProperties;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthUtil {

    private final HttpServletRequest request;
    private final TokenProperties tokenProperties;

    /**
     * 获取请求头中的 token
     */
    public String getHeaderToken() {
        String token = request.getHeader(tokenProperties.getHeader());
        if (StrUtil.isNotEmpty(token) && token.startsWith(AuthConsts.TOKEN_PREFIX)) {
            token = token.replace(AuthConsts.TOKEN_PREFIX, "");
        }
        return token;
    }
}
