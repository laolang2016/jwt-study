package com.laolang.shop.modules.auth.logic;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Sets;
import com.laolang.shop.common.consts.StatusCodeConst;
import com.laolang.shop.common.domain.R;
import com.laolang.shop.common.exception.BusinessException;
import com.laolang.shop.common.util.ServletKit;
import com.laolang.shop.modules.auth.domain.AuthUser;
import com.laolang.shop.modules.auth.domain.LoginUser;
import com.laolang.shop.modules.auth.rsp.LoginRsp;
import com.laolang.shop.modules.auth.service.TokenService;
import com.laolang.shop.modules.auth.util.AuthUtil;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthLogic {

    private final TokenService tokenService;
    private final HttpServletResponse response;
    private final AuthUtil authUtil;

    private Set<String> usernames;

    @PostConstruct
    public void init() {
        usernames = Sets.newHashSet();
        usernames.add("admin");
        usernames.add("laolang");
    }

    public LoginRsp login(LoginUser loginUser) {

        if (!usernames.contains(loginUser.getUsername())) {
            log.info("用户不存在");
            throw new BusinessException("用户名或密码错误");
        }
        if (!StrUtil.equals("123456", loginUser.getPassword())) {
            log.info("密码错误");
            throw new BusinessException("用户名或密码错误");
        }
        AuthUser authUser = new AuthUser();
        authUser.setUsername(loginUser.getUsername());

        // 生成 token
        String token = tokenService.buildToken(authUser);

        log.info("token:{}", token);
        LoginRsp rsp = new LoginRsp();
        rsp.setToken(token);
        return rsp;
    }

    public void logout() {
        try {
            String token = authUtil.getHeaderToken();
            if (StrUtil.isBlank(token)) {
                log.warn("token 不存在");
                ServletKit.writeJson(response, JSONUtil.toJsonStr(R.doOverdue()));
                return;
            }
            tokenService.removeToken(token);
        } catch (Exception e) {
            log.error("退出接口异常:{}", ExceptionUtils.getMessage(e));
            throw new BusinessException(StatusCodeConst.ERROR);
        }
    }
}
