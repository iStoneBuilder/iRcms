package com.stone.it.rcms.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jichen
 */
public class DateUtil {

    private static final String FORMAT = "yyyyMMddhhmmssSSS";

    public static String formatDate() {
        return formatDate(new Date());
    }

    public static String formatDate(Date date) {
        return formatDate(date, FORMAT);
    }

    public static String formatDate(String format) {
        return formatDate(new Date(), format);
    }

    public static String formatDate(Date date, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static Date addDaysToDate(Date date, int validDuration) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, validDuration);
        return calendar.getTime();
    }
}
