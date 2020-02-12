package com.lab.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * java.time包测试用例
 */
public class Java8TimeTest {

  static int printCount = 1;

  public static void dateCalculateTest() {
    LocalDateTime dateTime = LocalDateTime.now(Clock.system(ZoneId.systemDefault()));
    LocalDateTime datetime2 = dateTime.minusDays(2);

    printTest(dateTime);
    //2019-02-01 15:01:12
    printTest(datetime2);
    //2019-01-30 15:01:12
    printTest(datetime2.plusHours(3));
    //2019-01-30 18:01:12
    printTest(datetime2.minusWeeks(1));
    //2019-01-23 15:01:12
    printTest(datetime2.plus(1, ChronoUnit.MONTHS));
    //2019-02-28 15:01:12

    printTest(datetime2.compareTo(dateTime));
    //-1
    printTest(datetime2.withYear(2));
    //0002-01-30 15:01:12
    printTest(datetime2.isBefore(dateTime));
    //true

    Duration duration = Duration.ofDays(5);
    printTest(duration);
    //PT120H
    printTest(duration.plusHours(2).toMinutes());
    //7320
  }

  public static void dateFormatTest() {
    LocalDateTime dateTime = LocalDateTime.now(Clock.systemDefaultZone());

    printTest(dateTime);
    //2019-02-01 14:59:45
    printTest(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));
    //20190201
    printTest(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    //2019-02-01T14:59:45.997
    printTest(dateTime.format(DateTimeFormatter.ISO_WEEK_DATE));
    //2019-W05-5
    printTest(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
    //2019-02-01 14:59:45.997

    DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
    builder.appendPattern("yyyy-MM-dd");
    builder.parseStrict().toFormatter();
    printTest(dateTime.format(builder.parseStrict().toFormatter()));
    //2019-02-01
  }

  public static void dateStringTest() {
    LocalDateTime dateTime = LocalDateTime.now(Clock.system(ZoneId.systemDefault()));
    printTest(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
    //2019-02-01
    printTest(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    //2019-02-01T14:57:57.744
    printTest(dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
    //14:57:57.744

    printTest(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));
    //20190201
    printTest(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    //2019-02-01 14:57:57
    printTest(dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
    //19-2-1 下午2:57
    printTest(dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
    //2019-2-1 14:57:57
    printTest(dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.valueOf("MEDIUM"))));
    //2019-2-1 14:57:57

    printTest(LocalDateTime.parse("2019-12-03T10:15:30").toString());
    //2019-12-03T10:15:30
    printTest(LocalDate.parse("2019-12-03", DateTimeFormatter.ISO_LOCAL_DATE));
    //2019-12-03
    printTest(LocalTime.parse("10:15:30", DateTimeFormatter.ISO_LOCAL_TIME));
    //10:15:30
  }

  public static void date2OldTest() {

    LocalDateTime localDateTime = LocalDateTime.now();
    localDateTime.minusHours(2);
    printTest(localDateTime);
    //2019-02-01 14:55:06
    Date localDateTime2 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    printTest(localDateTime2.toString());
    //Fri Feb 01 14:55:06 CST 2019

    LocalDate localDate = LocalDate.now();
    printTest(localDate);
    //2019-02-01
    Date localDate2 = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    printTest(localDate2);
    //Fri Feb 01 00:00:00 CST 2019

    Date date = new Date();
    printTest(date);
    //Fri Feb 01 14:55:06 CST 2019
    LocalDateTime date2 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    printTest(date2);
    //2019-02-01 14:55:06

    LocalTime localTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toLocalTime();
    printTest(localTime);
    //14:55:06.360
  }

  public static void dateAndTime() {
    //从date转向time
    LocalDateTime localDateTime = LocalDateTime.now();
    printTest(localDateTime);
    //2019-02-01 14:52:26
    LocalDate date = localDateTime.toLocalDate();
    printTest(date);
    //2019-02-01
    LocalTime time = localDateTime.toLocalTime();
    printTest(time);
    //14:52:26.706

    //从time转向datetime
    LocalTime localTime = LocalTime.now();
    printTest(localTime);
    //14:52:26.727
    LocalDateTime dateTime = localTime.atDate(LocalDate.now());
    printTest(dateTime);
    //2019-02-01 14:52:26

    //从date转向datetime
    LocalDate localDate = LocalDate.now();
    printTest(localDate);
    //2019-02-01
    LocalDateTime dateTime1 = localDate.atTime(LocalTime.of(8, 32, 45));
    printTest(dateTime1);
    //2019-02-01 08:32:45
  }

  public static void zone2localTest() {
    //加时区
    LocalDateTime localDateTime = LocalDateTime.now();
    printTest(localDateTime);
    //2019-02-01 16:00:53
    printTest(localDateTime.atZone(ZoneId.of("Europe/Paris")));
    //2019-02-01T16:00:53.451+01:00[Europe/Paris]
    printTest(localDateTime.atZone(ZoneId.of("Asia/Shanghai")));
    //2019-02-01T16:00:53.451+08:00[Asia/Shanghai]

    //去除时区
    ZonedDateTime zonedDateTime = ZonedDateTime.now();
    printTest(zonedDateTime);
    //2019-02-01T16:00:53.477+08:00[Asia/Shanghai]
    printTest(zonedDateTime.toLocalDateTime());
    //2019-02-01 16:00:53
    printTest(zonedDateTime.toLocalDate());
    //2019-02-01

    //其他时区转到本地时区
    ZonedDateTime zonedDateTime1 = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.of("Europe/Paris"));
    printTest(zonedDateTime1);
    //2019-02-01T16:00:53.477+01:00[Europe/Paris]
    ZonedDateTime zonedLocal = zonedDateTime1.withZoneSameInstant(ZoneId.of("Asia/Shanghai"));
    printTest(zonedLocal);
    //2019-02-01T23:00:53.477+08:00[Asia/Shanghai]

    //本地时区转到其他时区
    LocalDateTime localDateTime2 = LocalDateTime.now(ZoneId.systemDefault());
    printTest(localDateTime2);
    //2019-02-01 16:00:53
    ZonedDateTime zonedDateTime13 = localDateTime2.atZone(ZoneId.systemDefault());
    printTest(zonedDateTime13);
    //2019-02-01T16:00:53.477+08:00[Asia/Shanghai]
    ZonedDateTime zonedDateTime2 = zonedDateTime13.withZoneSameInstant(ZoneId.of("Europe/Paris"));
    printTest(zonedDateTime2);
    //2019-02-01T09:00:53.477+01:00[Europe/Paris]
  }

  public static void printTest(Object print) {
    printCount = printCount + 1;
    if (print instanceof LocalDateTime) {
      LocalDateTime localDateTime = (LocalDateTime) print;
      System.out.println(printCount + " : " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    } else {
      System.out.println(printCount + " : " + print);
    }
  }

  public static void main(String[] args) {
    Java8TimeTest.zone2localTest();
  }
}
