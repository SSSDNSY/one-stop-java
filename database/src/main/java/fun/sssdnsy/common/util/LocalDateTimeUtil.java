
package fun.sssdnsy.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateTimeUtil {
    public LocalDateTimeUtil() {
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date localDate2Date(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Long localDateTime2TimeMillis(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static Long localDate2TimeMillis(LocalDate localDate) {
        return localDate == null ? null : localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static Long localDateTime2TimeSecond(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        return date == null ? null : LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDateTime timeMillis2LocalDateTime(Long timeMillis) {
        return timeMillis == null ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), ZoneId.systemDefault());
    }

    public static LocalDate date2LocalDate(Date date) {
        return date == null ? null : LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate timeMillis2LocalDate(Long timeMillis) {
        return timeMillis == null ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), ZoneId.systemDefault()).toLocalDate();
    }

    public static long localDate2MaxTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    public static long localDate2MinTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }
}
