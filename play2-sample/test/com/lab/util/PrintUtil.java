package com.lab.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class PrintUtil {

  static int printCount = 1;

  public static void printTest(Object print) {
    printCount = printCount + 1;
    if (print instanceof LocalDateTime) {
      LocalDateTime localDateTime = (LocalDateTime) print;
      System.out.println(printCount + " : " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    } else {
      System.out.println(printCount + " : " + print);
    }
  }
}
