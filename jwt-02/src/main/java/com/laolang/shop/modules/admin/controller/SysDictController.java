package com.laolang.shop.modules.admin.controller;

import com.google.common.collect.Maps;
import com.laolang.shop.common.domain.R;
import com.laolang.shop.modules.auth.business.AuthBusiness;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("admin/sysdict")
@RestController
public class SysDictController {

    private final AuthBusiness authBusiness;

    @GetMapping("info")
    public R<Map<String, Object>> info() {
        log.info("admin sysdict info");
        Map<String, Object> body = Maps.newHashMap();

        // 获取登录用户信息
        body.put("userId", authBusiness.getUserId());
        body.put("username", authBusiness.getAuthUser().getUsername());

        return R.ok(body);
    }
}
