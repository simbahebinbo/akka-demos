package com.lab.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateUtil {

  final static String defaultDateFormat = "yyyy-MM-dd HH:mm:SS";

  public static String currentDate() {
    LocalDate localDate = LocalDate.now();
    return localDate.toString();
  }

}
