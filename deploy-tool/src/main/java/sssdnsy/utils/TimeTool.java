package sssdnsy.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @author pengzh
 * @date 2020-03-25
 */
public final class TimeTool {
    public static final long DAYMILLI = 86400000L;
    public static final long HOURMILLI = 3600000L;
    public static final long MINUTEMILLI = 60000L;
    public static final long SECONDMILLI = 1000L;
    public static final String LAST_SECOND = " 23:59:59";
    public static final String FOREVER_TIME = "2050-12-31 23:59:59";
    public static final transient int BEFORE = 1;
    public static final transient int AFTER = 2;
    public static final transient int EQUAL = 3;
    public static final String TIME_PATTERN_LONG = "dd/MMM/yyyy:HH:mm:ss +0900";
    public static final String TIME_PATTERN_LONG2 = "dd/MM/yyyy:HH:mm:ss +0900";
    public static final String TIME_PATTERN_SHORT = "dd/MM/yy HH:mm:ss";
    public static final String TIME_PATTERN_SHORT_1 = "yyyy/MM/dd HH:mm";
    public static final String TIME_PATTERN_SHORT_2 = "yyyy年MM月dd日 HH:mm:ss";
    public static final String TIME_PATTERN_SESSION = "yyyyMMddHHmmss";
    public static final String TIME_PATTERN_MILLISECOND = "yyyyMMddHHmmssSSS";
    public static final String DATE_FMT_0 = "yyyyMMdd";
    public static final String DATE_FMT_1 = "yyyy/MM/dd";
    public static final String DATE_FMT_2 = "yyyy/MM/dd hh:mm:ss";
    public static final String DATE_FMT_3 = "yyyy-MM-dd";
    public static final String DATE_FMT_4 = "yyyy年MM月dd日";
    public static final String DATE_FMT_5 = "yyyy-MM-dd HH";
    public static final String DATE_FMT_6 = "yyyy-MM";
    public static final String DATE_FMT_7 = "MM月dd日 HH:mm";
    public static final String DATE_FMT_8 = "HH:mm:ss";
    public static final String DATE_FMT_9 = "yyyy.MM.dd";
    public static final String DATE_FMT_10 = "HH:mm";
    public static final String DATE_FMT_11 = "yyyy.MM.dd HH:mm:ss";
    public static final String DATE_FMT_12 = "MM月dd日";
    public static final String DATE_FMT_13 = "yyyy年MM月dd日HH时mm分";
    public static final String DATE_FMT_14 = "yyyyMM";
    public static final String DATE_FMT_15 = "MM-dd HH:mm:ss";
    public static final String DATE_FMT_16 = "yyyyMMddHHmm";
    public static final String DATE_FMT_17 = "HHmmss";
    public static final String DATE_FMT_18 = "yyyy";
    public static final String TIME_STANDARD = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_STANDARD = "yyyy-MM-dd";

    public TimeTool() {
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDate today() {
        LocalDateTime now = now();
        return now.toLocalDate();
    }

    public static LocalDateTime foreverTime() {
        return stringToTime("2050-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
    }

    public static String format(LocalDateTime time, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return time.format(formatter);
    }

    public static String format(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    public static LocalDate timeToDate(LocalDateTime time) {
        return time.toLocalDate();
    }

    public static LocalDateTime dateToTime(LocalDate date) {
        LocalDateTime now = now();
        LocalTime time = now.toLocalTime();
        return LocalDateTime.of(date, time);
    }

    public static LocalDateTime stringToTime(String time, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, formatter);
    }

    public static LocalDate stringToDate(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, formatter);
    }

    public static int compare(LocalDateTime one, LocalDateTime another) {
        if (one.isAfter(another)) {
            return 1;
        } else {
            return one.isBefore(another) ? -1 : 0;
        }
    }

    public static int compare(LocalDate one, LocalDate another) {
        if (one.isAfter(another)) {
            return 1;
        } else {
            return one.isBefore(another) ? -1 : 0;
        }
    }

    public static long between(LocalDateTime one, LocalDateTime another) {
        return Duration.between(one, another).toDays();
    }

    public static long between(LocalDate one, LocalDate another) {
        return Duration.between(one, another).toDays();
    }

    public static long betweenMonths(LocalDateTime one, LocalDateTime another) {
        return one.until(another, ChronoUnit.MONTHS);
    }

    public static long betweenMonths(LocalDate one, LocalDate another) {
        return one.until(another, ChronoUnit.MONTHS);
    }

    public static long betweenYears(LocalDateTime one, LocalDateTime another) {
        return one.until(another, ChronoUnit.YEARS);
    }

    public static long betweenYears(LocalDate one, LocalDate another) {
        return one.until(another, ChronoUnit.YEARS);
    }

    public static long betweenSeconds(LocalDateTime one, LocalDateTime another) {
        return Duration.between(one, another).getSeconds();
    }

    public static long betweenMinutes(LocalDateTime one, LocalDateTime another) {
        return Duration.between(one, another).toMinutes();
    }

    public static LocalDateTime addSeconds(LocalDateTime time, long seconds) {
        return time.plusSeconds(seconds);
    }

    public static LocalDateTime addMinutes(LocalDateTime time, long minutes) {
        return time.plusMinutes(minutes);
    }

    public static LocalDateTime addHours(LocalDateTime time, long hours) {
        return time.plusHours(hours);
    }

    public static LocalDateTime addDays(int offset) {
        LocalDateTime now = now();
        return now.plusDays((long) offset);
    }

    public static LocalDateTime addDays(LocalDateTime time, int offset) {
        return time.plusDays((long) offset);
    }

    public static LocalDate addDays(LocalDate date, int offset) {
        return date.plusDays((long) offset);
    }

    public static LocalDateTime getLastSecondDay(LocalDateTime time) {
        return LocalDateTime.of(time.toLocalDate(), LocalTime.MAX);
    }

    public static LocalDateTime getLastSecondDay(LocalDateTime time, int offset) {
        return LocalDateTime.of(addDays(time, offset).toLocalDate(), LocalTime.MAX);
    }

    public static LocalDateTime getFirstSecondDay() {
        return LocalDateTime.of(today(), LocalTime.MIN);
    }

    public static LocalDateTime getFirstSecondDay(LocalDateTime time) {
        return LocalDateTime.of(time.toLocalDate(), LocalTime.MIN);
    }

    public static LocalDateTime getFirstSecondDay(LocalDateTime time, int offset) {
        return LocalDateTime.of(addDays(time, offset).toLocalDate(), LocalTime.MIN);
    }

    public static LocalDateTime addMonths(int offset) {
        return now().plusMonths((long) offset);
    }

    public static LocalDateTime addMonths(LocalDateTime time, int offset) {
        return time.plusMonths((long) offset);
    }

    public static LocalDate addMonths(LocalDate date, int offset) {
        return date.plusMonths((long) offset);
    }

    public static LocalDateTime getLastSecondMonth() {
        return LocalDateTime.of(today().with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX);
    }

    public static LocalDateTime getLastSecondMonth(int offset) {
        return LocalDateTime.of(addMonths(today(), offset).with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX);
    }

    public static LocalDateTime getLastSecondMonth(LocalDateTime time) {
        return LocalDateTime.of(time.toLocalDate().with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX);
    }

    public static LocalDateTime getLastSecondMonth(LocalDateTime time, int offset) {
        return LocalDateTime.of(addMonths(time, offset).toLocalDate().with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX);
    }

    public static LocalDateTime getLastSecondMonth(LocalDate date, int offset) {
        return LocalDateTime.of(addMonths(date, offset).with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX);
    }

    public static LocalDateTime getFirstSecondMonth() {
        return LocalDateTime.of(today().with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
    }

    public static LocalDateTime getFirstSecondMonth(LocalDateTime time) {
        return LocalDateTime.of(time.toLocalDate().with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
    }

    public static LocalDateTime getFirstSecondMonth(LocalDateTime time, int offset) {
        return LocalDateTime.of(addMonths(time, offset).toLocalDate().with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
    }

    public static LocalDateTime getFirstSecondMonth(int offset) {
        return LocalDateTime.of(addMonths(now(), offset).toLocalDate().with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
    }

    public static LocalDateTime getFirstSecondMonth(LocalDate date, int offset) {
        return LocalDateTime.of(addMonths(date, offset).with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
    }

    public static LocalDate getFirstDayMonth() {
        return today().with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate getFirstDayMonth(LocalDateTime time) {
        return time.toLocalDate().with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate getFirstDayMonth(LocalDate date) {
        return date.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate getFirstDayMonth(LocalDate date, int offset) {
        return addMonths(date, offset).with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate getFirstDayMonth(LocalDateTime time, int offset) {
        return addMonths(time, offset).toLocalDate().with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate getLastDayMonth() {
        return today().with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate getLastDayMonth(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate getLastDayMonth(LocalDateTime time) {
        return time.toLocalDate().with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate getLastDayMonth(LocalDate date, int num) {
        return addMonths(date, num).with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate getLastDayMonth(LocalDateTime time, int num) {
        return addMonths(time, num).toLocalDate().with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDateTime addYears(int offset) {
        return now().plusYears((long) offset);
    }

    public static LocalDateTime addYears(LocalDateTime time, int offset) {
        return time.plusYears((long) offset);
    }

    public static LocalDate addYears(LocalDate date, int offset) {
        return date.plusYears((long) offset);
    }

    public static LocalDate getLastDayYear() {
        return today().with(TemporalAdjusters.lastDayOfYear());
    }

    public static LocalDate getLastDayYear(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfYear());
    }

    public static LocalDateTime getLastSecondYear() {
        return LocalDateTime.of(today().with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX);
    }

    public static LocalDateTime getLastSecondYear(LocalDateTime time) {
        return LocalDateTime.of(time.toLocalDate().with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX);
    }

    public static LocalDateTime getLastSecondYear(LocalDateTime time, int offset) {
        return LocalDateTime.of(addYears(time, offset).toLocalDate().with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX);
    }

    public static LocalDateTime getLastSecondYear(LocalDate date, int offset) {
        return LocalDateTime.of(addYears(date, offset).with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX);
    }

    public static LocalDateTime getFirstSecondYear() {
        return LocalDateTime.of(today().with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIN);
    }

    public static LocalDateTime getFirstSecondYear(LocalDateTime time) {
        return LocalDateTime.of(time.toLocalDate().with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIN);
    }

    public static LocalDateTime getFirstSecondYear(LocalDateTime time, int offset) {
        return LocalDateTime.of(addYears(time, offset).toLocalDate().with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIN);
    }

    public static LocalDateTime getFirstSecondYear(LocalDate date, int offset) {
        return LocalDateTime.of(addYears(date, offset).with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIN);
    }

    public static LocalDateTime cancelTime(int cancelTag, LocalDateTime cancelAbsoluteTime) {
        if (cancelAbsoluteTime != null) {
            return cancelAbsoluteTime;
        } else {
            switch (cancelTag) {
                case 0:
                    return addSeconds(now(), -1L);
                case 1:
                    return getLastSecondDay(addDays(-1));
                case 2:
                    return getLastSecondMonth();
                case 3:
                default:
                    return null;
            }
        }
    }

    public static LocalDateTime cancelTime(String cancelTag, String cancelAbsoluteTime) {
        if (cancelTag == null) {
            return null;
        } else {
            LocalDateTime absoluteTime = null;
            if (cancelAbsoluteTime != null) {
                absoluteTime = stringToTime(cancelAbsoluteTime, "yyyy-MM-dd HH:mm:ss");
            }

            return cancelTime(Integer.valueOf(cancelTag), absoluteTime);
        }
    }

    public static LocalDateTime startTime(int enableTag, LocalDateTime absoluteTime, int offset, int unit) {
        switch (enableTag) {
            case 0:
                return startTimeOffset(now(), offset, unit);
            case 1:
            case 3:
                return startTimeOffset(getFirstSecondMonth((LocalDateTime) now(), 1), offset, unit);
            case 2:
                return startTimeOffset(getFirstSecondDay(addDays(1)), offset, unit);
            case 4:
                return absoluteTime;
            case 5:
            default:
                return now();
            case 6:
                int nowWeekDay = today().getDayOfWeek().getValue();
                if (offset < nowWeekDay) {
                    offset = offset - nowWeekDay + 7;
                } else {
                    offset -= nowWeekDay;
                }

                return getFirstSecondDay(now(), offset);
        }
    }

    public static LocalDateTime startTimeOffset(LocalDateTime time, int offset, int unit) {
        if (offset == 0) {
            return time;
        } else {
            switch (unit) {
                case 0:
                case 1:
                    return getFirstSecondDay(addDays(time, offset));
                case 2:
                case 3:
                    return getFirstSecondDay(addMonths(time, offset));
                case 4:
                    return getFirstSecondDay(addMonths(time, offset * 12));
                case 5:
                    return getLastSecondYear(addYears(offset - 1));
                case 6:
                    return addHours(time, (long) offset);
                case 7:
                    return addMinutes(time, (long) offset);
                default:
                    return time;
            }
        }
    }

    public static LocalDateTime endTime(LocalDateTime startTime, int endEnableTag, LocalDateTime absoluteTime, int offset, int unit) {
        switch (endEnableTag) {
            case 0:
                return absoluteTime;
            case 1:
                return endTimeOffset(startTime, offset, unit);
            case 2:
            default:
                return foreverTime();
            case 3:
                return getLastSecondDay(startTime);
        }
    }

    public static LocalDateTime endTimeOffset(LocalDateTime time, int offset, int unit) {
        if (offset == 0) {
            return time;
        } else {
            switch (unit) {
                case 0:
                case 1:
                    return getLastSecondDay(addDays(time, offset));
                case 2:
                case 3:
                    return getLastSecondDay(addMonths(time, offset));
                case 4:
                    return getLastSecondDay(addMonths(time, offset * 12));
                case 5:
                    return getLastSecondYear(addYears(offset - 1));
                case 6:
                    return addHours(time, (long) offset);
                case 7:
                    return addMinutes(time, (long) offset);
                default:
                    return time;
            }
        }
    }

}
