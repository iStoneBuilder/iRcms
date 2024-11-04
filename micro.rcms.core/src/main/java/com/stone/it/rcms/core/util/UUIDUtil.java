package com.stone.it.rcms.core.util;

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
        return DateUtil.formatDate() + "-" + getUuid();
    }

    public static String getYearMonthDayUuid() {
        return DateUtil.formatDate("yyyyMMdd") + "-" + getUuid();
    }

}
