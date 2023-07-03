package com.laolang.shop.modules.auth.controller;

import com.laolang.shop.common.domain.R;
import com.laolang.shop.modules.auth.domain.LoginUser;
import com.laolang.shop.modules.auth.logic.AuthLogic;
import com.laolang.shop.modules.auth.rsp.LoginRsp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("auth")
@RestController
public class AuthController {
    private final AuthLogic authLogic;

    /**
     * 登录接口
     */
    @PostMapping("login")
    public R<LoginRsp> login(@RequestBody LoginUser loginUser) {
        return R.ok(authLogic.login(loginUser));
    }
}
