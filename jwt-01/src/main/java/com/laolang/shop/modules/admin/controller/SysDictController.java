package com.laolang.shop.modules.admin.controller;

import com.laolang.shop.common.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("admin/sysdict")
@RestController
public class SysDictController {

    @GetMapping("info")
    public R<String> info(){
        log.info("admin sysdict info");
        return R.ok("ok");
    }
}
