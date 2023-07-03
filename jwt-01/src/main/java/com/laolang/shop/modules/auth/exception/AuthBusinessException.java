package com.laolang.shop.modules.auth.exception;

import com.laolang.shop.common.consts.IBizCode;
import com.laolang.shop.common.exception.BusinessException;

public class AuthBusinessException extends BusinessException {

    public AuthBusinessException(IBizCode bizCode) {
        super(bizCode);
    }
}
