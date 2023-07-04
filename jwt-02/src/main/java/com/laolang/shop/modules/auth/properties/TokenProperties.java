package com.laolang.shop.modules.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "token")
public class TokenProperties {
    private String header = "Authorization";
    private String secret = "secret";
    private Integer expireTime = 30;
}
