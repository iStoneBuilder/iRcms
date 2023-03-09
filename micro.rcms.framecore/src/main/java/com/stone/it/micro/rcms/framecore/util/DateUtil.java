package com.stone.it.micro.rcms.framecore.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jichen
 */
public class DateUtil {

  private static final String format = "yyyyMMddhhmmssSSS";

  static String getYearMonthDay() {
    return getYearMonthDay(new Date());
  }

  static String getYearMonthDay(Date date) {
    return getYearMonthDay(date, format);
  }

  static String getYearMonthDay(String format) {
    return getYearMonthDay(new Date(), format);
  }

  static String getYearMonthDay(Date date, String format) {
    DateFormat formatter = new SimpleDateFormat(format);
    return formatter.format(date);
  }

}
