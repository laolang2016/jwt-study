package com.laolang.shop.modules.auth.logic;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.laolang.shop.common.exception.BusinessException;
import com.laolang.shop.modules.auth.domain.AuthUser;
import com.laolang.shop.modules.auth.domain.LoginUser;
import com.laolang.shop.modules.auth.rsp.LoginRsp;
import com.laolang.shop.modules.auth.service.TokenService;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthLogic {

    private final TokenService tokenService;

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
        String token = tokenService.buildToken(authUser);
        log.info("token:{}", token);
        LoginRsp rsp = new LoginRsp();
        rsp.setToken(token);
        return rsp;
    }
}
