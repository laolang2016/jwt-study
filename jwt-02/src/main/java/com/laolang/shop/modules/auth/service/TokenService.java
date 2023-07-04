package com.laolang.shop.modules.auth.service;

import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Maps;
import com.laolang.shop.common.cache.redis.RedisPrefix;
import com.laolang.shop.common.cache.redis.RedisUtil;
import com.laolang.shop.modules.auth.consts.logic.AuthBizCode;
import com.laolang.shop.modules.auth.domain.AuthUser;
import com.laolang.shop.modules.auth.exception.AuthBusinessException;
import com.laolang.shop.modules.auth.properties.TokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * token 工具服务
 */
@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProperties tokenProperties;
    private final RedisUtil redisUtil;

    /**
     * 生成 token
     */
    public String buildToken(AuthUser authUser) {
        authUser.setUuid(IdUtil.fastSimpleUUID());
        Map<String, Object> claims = Maps.newHashMap();
        claims.put("username", authUser.getUsername());
        String token = Jwts.builder()
                .setClaims(claims) // 自定义数据
                .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecret()).compact();
        refreshToken(authUser);
        return token;
    }

    /**
     * 校验 token
     */
    public AuthUser verify(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(tokenProperties.getSecret()).parseClaimsJws(token).getBody();
            String username = claims.get("username", String.class);
            AuthUser authUser = redisUtil.getCacheObject(getRedisKey(username));
            if (Objects.isNull(authUser)) {
                throw new AuthBusinessException(AuthBizCode.token_not_exist);
            }
            return authUser;
        } catch (ExpiredJwtException e) {
            throw new AuthBusinessException(AuthBizCode.login_expired);
        } catch (Exception e) {
            if (e instanceof AuthBusinessException) {
                throw e;
            }
            throw new AuthBusinessException(AuthBizCode.error_token);
        }
    }

    /**
     * 删除 token
     *
     * @param token 请求头中的 token
     */
    public void removeToken(String token) {
        AuthUser authUser = verify(token);
        redisUtil.del(getRedisKey(authUser.getUsername()));
    }

    /**
     * 刷新 token
     *
     * @param authUser 用户信息, 将来可以携带一些其他信息, 比如角色信息
     */
    private void refreshToken(AuthUser authUser) {
        redisUtil.setCacheObject(getRedisKey(authUser.getUsername()), authUser, tokenProperties.getExpireTime(),
                TimeUnit.MINUTES);
    }

    /**
     * 获取 token redis key
     *
     * @param username 用户名
     * @return 前缀
     */
    private String getRedisKey(String username) {
        return RedisPrefix.AUTH_TOKEN_PREFIX + username;
    }


}
