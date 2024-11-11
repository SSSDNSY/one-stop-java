
package fun.sssdnsy.common.util;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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

    public DateFormatUtil() {
    }

    public static Date parseDate(String pattern, String dateStr) throws ParseException {
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
}
