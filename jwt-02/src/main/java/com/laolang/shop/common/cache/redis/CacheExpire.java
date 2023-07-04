package com.laolang.shop.common.cache.redis;

import java.util.concurrent.TimeUnit;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CacheExpire {
    /**
     * 过期时间相关枚举
     */
    public enum ExpireEnum {
        //未读消息的有效期为30天
        UNREAD_MSG(30L, TimeUnit.DAYS),
        // auth token
        AUTH_TOKEN(7L, TimeUnit.DAYS);

        /**
         * 过期时间
         */
        private final Long time;
        /**
         * 时间单位
         */
        private final TimeUnit timeUnit;

        ExpireEnum(Long time, TimeUnit timeUnit) {
            this.time = time;
            this.timeUnit = timeUnit;
        }

        public Long getTime() {
            return time;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }
    }
}

