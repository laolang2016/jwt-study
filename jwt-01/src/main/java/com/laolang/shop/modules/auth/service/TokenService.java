package com.laolang.shop.modules.auth.service;

import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Maps;
import com.laolang.shop.common.consts.GlobalConst;
import com.laolang.shop.modules.auth.consts.logic.AuthBizCode;
import com.laolang.shop.modules.auth.domain.AuthUser;
import com.laolang.shop.modules.auth.exception.AuthBusinessException;
import com.laolang.shop.modules.auth.properties.TokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * token 工具服务
 */
@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProperties tokenProperties;

    /**
     * 生成 token
     */
    public String buildToken(AuthUser authUser) {
        authUser.setUuid(IdUtil.fastSimpleUUID());
        Map<String, Object> claims = Maps.newHashMap();
        Date iat = new Date();
        Date exp = new Date(iat.getTime() + tokenProperties.getExpireTime() * 60 * 1000);
        claims.put(GlobalConst.LOGIN_USER_KEY, authUser.getUuid());
        claims.put("username", authUser.getUsername());
        return Jwts.builder()
                .setIssuedAt(iat) // 签发时间
                .setExpiration(exp) // 过期时间
                .setClaims(claims) // 自定义数据
                .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecret()).compact();
    }

    /**
     * 校验 token
     */
    public AuthUser verify(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(tokenProperties.getSecret()).parseClaimsJws(token).getBody();
            AuthUser authUser = new AuthUser();
            authUser.setUsername(claims.get("username", String.class));
            authUser.setUuid(claims.get(GlobalConst.LOGIN_USER_KEY, String.class));
            return authUser;
        } catch (ExpiredJwtException e) {
            throw new AuthBusinessException(AuthBizCode.login_expired);
        } catch (Exception e) {
            throw new AuthBusinessException(AuthBizCode.error_token);
        }
    }
}
