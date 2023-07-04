package com.laolang.shop.modules.auth.exception;

import com.laolang.shop.common.exception.BusinessException;
import com.laolang.shop.modules.auth.consts.logic.AuthBizCode;

public class AuthBusinessException extends BusinessException {

    public AuthBusinessException(AuthBizCode bizCode) {
        super(bizCode);
    }
}
