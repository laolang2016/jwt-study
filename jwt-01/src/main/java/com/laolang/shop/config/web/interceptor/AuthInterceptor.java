package com.laolang.shop.config.web.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.laolang.shop.common.consts.GlobalConst;
import com.laolang.shop.common.domain.R;
import com.laolang.shop.common.util.ServletKit;
import com.laolang.shop.modules.auth.consts.logic.AuthBizCode;
import com.laolang.shop.modules.auth.consts.logic.AuthConsts;
import com.laolang.shop.modules.auth.domain.AuthUser;
import com.laolang.shop.modules.auth.exception.AuthBusinessException;
import com.laolang.shop.modules.auth.properties.TokenProperties;
import com.laolang.shop.modules.auth.service.TokenService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;
    private final TokenProperties tokenProperties;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {
        try {
            String token = getToken(request);
            if (StrUtil.isBlank(token)) {
                log.warn("token 不存在");
                ServletKit.writeJson(response, JSONUtil.toJsonStr(R.doOverdue()));
                return false;
            }
            AuthUser authUser = tokenService.verify(token);
            // 填充 authUser 信息
            if (StrUtil.equals(authUser.getUsername(), "admin")) {
                authUser.setId(1L);
            } else {
                authUser.setId(2L);
            }
            // TODO 一些其他操作, 比如刷新 token

            // 放入到 request 中, 供后续使用
            request.setAttribute(AuthConsts.AUTH_USER_ATTR_NAME, authUser);
        } catch (AuthBusinessException e) {
            log.warn("token 解析异常");
            if (StrUtil.equals(AuthBizCode.login_expired.getCode(), e.getCode())) {
                log.warn("token 过期");
                ServletKit.writeJson(response, JSONUtil.toJsonStr(R.doOverdue()));
            } else {
                log.warn("非法的 token");
                ServletKit.writeJson(response, JSONUtil.toJsonStr(R.failed("身份验证异常")));
            }
            return false;
        } catch (Exception e) {
            log.error("auth 拦截器错误:{}", ExceptionUtils.getMessage(e));
            ServletKit.writeJson(response, JSONUtil.toJsonStr(R.error("服务器内部错误")));
            return false;
        }
        return true;
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(tokenProperties.getHeader());
        if (StrUtil.isNotEmpty(token) && token.startsWith(GlobalConst.TOKEN_PREFIX)) {
            token = token.replace(GlobalConst.TOKEN_PREFIX, "");
        }
        return token;
    }
}
