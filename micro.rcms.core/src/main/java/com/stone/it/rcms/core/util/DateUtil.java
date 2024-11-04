package com.stone.it.rcms.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jichen
 */
public class DateUtil {

    private static final String format = "yyyyMMddhhmmssSSS";

    public static String formatDate() {
        return formatDate(new Date());
    }

    public static String formatDate(Date date) {
        return formatDate(date, format);
    }

    public static String formatDate(String format) {
        return formatDate(new Date(), format);
    }

    public static String formatDate(Date date, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

}
