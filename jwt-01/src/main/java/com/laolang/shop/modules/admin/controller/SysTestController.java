package com.laolang.shop.modules.admin.controller;

import com.google.common.collect.Maps;
import com.laolang.shop.common.annotation.AnonymousAccess;
import com.laolang.shop.common.domain.R;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("admin/test")
@RestController
public class SysTestController {

    @AnonymousAccess
    @GetMapping("testAnonymousAccess")
    public R<Map<String, Object>> testAnonymousAccess() {
        log.info("admin sysdict info");
        Map<String, Object> body = Maps.newHashMap();

        body.put("msg","这是一个匿名访问接口");

        return R.ok(body);
    }
}
