package com.laolang.shop.modules.admin.controller;

import com.google.common.collect.Maps;
import com.laolang.shop.common.annotation.AnonymousAccess;
import com.laolang.shop.common.cache.redis.RedisUtil;
import com.laolang.shop.common.domain.R;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("admin/test")
@RestController
public class SysTestController {

    private final RedisUtil redisUtil;

    @AnonymousAccess
    @GetMapping("testAnonymousAccess")
    public R<Map<String, Object>> testAnonymousAccess() {
        log.info("admin sysdict info");
        Map<String, Object> body = Maps.newHashMap();

        body.put("msg", "这是一个匿名访问接口");

        redisUtil.set("msg", body);


        return R.ok(body);
    }
}
