package com.laolang.shop.common.util;

import cn.hutool.core.util.StrUtil;
import com.laolang.shop.common.consts.GlobalConst;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtil {

    public static boolean isSuperAdmin(String account) {
        return StrUtil.equals(GlobalConst.ADMIN_ACCOUNT, account);
    }
}
