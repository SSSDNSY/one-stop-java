
package fun.sssdnsy.common.util;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatUtil {
    public static final String PATTERN_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
    public static final String PATTERN_ISO_ON_SECOND = "yyyy-MM-dd'T'HH:mm:ssZZ";
    public static final String PATTERN_ISO_ON_DATE = "yyyy-MM-dd";
    public static final String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String PATTERN_DEFAULT_ON_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final FastDateFormat ISO_FORMAT = FastDateFormat.getInstance(PATTERN_ISO);
    public static final FastDateFormat ISO_ON_SECOND_FORMAT = FastDateFormat.getInstance(PATTERN_ISO_ON_SECOND);
    public static final FastDateFormat ISO_ON_DATE_FORMAT = FastDateFormat.getInstance(PATTERN_ISO_ON_DATE);
    public static final FastDateFormat DEFAULT_FORMAT = FastDateFormat.getInstance(PATTERN_DEFAULT);
    public static final FastDateFormat DEFAULT_ON_SECOND_FORMAT = FastDateFormat.getInstance(PATTERN_DEFAULT_ON_SECOND);


    /**
     * 格式化输出date到string
     *
     * @param date  时间
     * @param style 格式化参数
     * @return
     */
    public static String format(LocalDateTime date, String style) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(style);
        return date.format(dateFormat);
    }


    /**
     * 根据长整型毫秒数和指定的时间格式返回时间字符串。
     *
     * @param millisecond 毫秒数
     * @param style       指定格式
     * @return
     */
    public static String format(long millisecond, String style) {
        Instant instant = Instant.ofEpochMilli(millisecond);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime date = LocalDateTime.ofInstant(instant, zone);
        return format(date, style);
    }

    /**
     * 取当前系统日期，并按指定格式或者是默认格式返回
     *
     * @param style
     * @return
     */
    public static String getNowByString(String style) {
        if (null == style || "".equals(style)) {
            style = "yyyy-MM-dd HH:mm:ss";
        }
        return format(LocalDateTime.now(), style);
    }

    public static Date parse(String pattern, String dateStr) throws ParseException {
        return FastDateFormat.getInstance(pattern).parse(dateStr);
    }

    public static LocalDateTime parseLocalDateTimeFromUTCZero(String dateTimeStr) {
        Instant instant = Instant.parse(dateTimeStr);
        return LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
    }

    public static String formatDate(String pattern, Date date) {
        return FastDateFormat.getInstance(pattern).format(date);
    }

    public static String formatDate(String pattern, long date) {
        return FastDateFormat.getInstance(pattern).format(date);
    }

    public static String formatDuration(Date startDate, Date endDate) {
        return DurationFormatUtils.formatDurationHMS(endDate.getTime() - startDate.getTime());
    }

    public static String formatDuration(long durationMillis) {
        return DurationFormatUtils.formatDurationHMS(durationMillis);
    }

    public static String formatDurationOnSecond(Date startDate, Date endDate) {
        return DurationFormatUtils.formatDuration(endDate.getTime() - startDate.getTime(), "HH:mm:ss");
    }

    public static String formatDurationOnSecond(long durationMillis) {
        return DurationFormatUtils.formatDuration(durationMillis, "HH:mm:ss");
    }

    public static String formatFriendlyTimeSpanByNow(Date date) {
        return formatFriendlyTimeSpanByNow(date.getTime());
    }

    public static String formatFriendlyTimeSpanByNow(long timeStampMillis) {
        long now = System.currentTimeMillis();
        long span = now - timeStampMillis;
        if (span < 0L) {
            return String.format("%tc", timeStampMillis);
        } else if (span < 1000L) {
            return "刚刚";
        } else if (span < 60000L) {
            return String.format("%d秒前", span / 1000L);
        } else if (span < 3600000L) {
            return String.format("%d分钟前", span / 60000L);
        } else {
            long wee = DateUtil.beginOfDate(new Date(now)).getTime();
            if (timeStampMillis >= wee) {
                return String.format("今天%tR", timeStampMillis);
            } else {
                return timeStampMillis >= wee - 86400000L ? String.format("昨天%tR", timeStampMillis) : String.format("%tF", timeStampMillis);
            }
        }
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
