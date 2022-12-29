package com.stone.it.rcms.base.util;

import java.util.UUID;

/**
 * @author jichen
 */
public class UUIDUtil {

    /**
     * UUID
     *
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * TIMER + "-" + UUID
     *
     * @return
     */
    public static String getTimerUuid() {
        return DateUtil.getYearMonthDay() + "-" + getUuid();
    }

}
