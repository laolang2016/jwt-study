package com.laolang.shop.modules.auth.business;

import com.laolang.shop.modules.auth.consts.logic.AuthBizCode;
import com.laolang.shop.modules.auth.consts.logic.AuthConsts;
import com.laolang.shop.modules.auth.domain.AuthUser;
import com.laolang.shop.modules.auth.exception.AuthBusinessException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthBusiness {

    private final HttpServletRequest request;

    public Long getUserId() {
        return getAuthUser().getId();
    }

    public AuthUser getAuthUser() {
        Object o = request.getAttribute(AuthConsts.AUTH_USER_ATTR_NAME);
        if (Objects.isNull(o)) {
            throw new AuthBusinessException(AuthBizCode.login_expired);
        }
        return (AuthUser) o;
    }
}
