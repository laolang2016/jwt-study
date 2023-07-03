package com.laolang.shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Jwt01Application {

    public static void main(String[] args) {
        SpringApplication.run(Jwt01Application.class);
        log.info("jwt 01 is running...");
    }
}
