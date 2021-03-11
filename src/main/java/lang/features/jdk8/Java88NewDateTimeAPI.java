package lang.features.jdk8;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author pengzh
 * @date 2020-03-03
 * <p>
 * Instant         时间戳
 * Duration        持续时间、时间差
 * LocalDate       只包含日期，比如：2018-09-24
 * LocalTime       只包含时间，比如：10:32:10
 * LocalDateTime   包含日期和时间，比如：2018-09-24 10:32:10
 * Peroid          时间段
 * ZoneOffset      时区偏移量，比如：+8:00
 * ZonedDateTime   带时区的日期时间
 * Clock           时钟，可用于获取当前时间戳
 * java.time.format.DateTimeFormatter      时间格式化类
 */
public class Java88NewDateTimeAPI {
    public static void main(String[] args) {


        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("当前时间" + ldt);

//        System.out.println(ldt.format("yyyy-MM-dd hh24:mi:ss"));
        System.out.println(ldt.toLocalDate());
        System.out.println(LocalDate.parse("2020-11-11") + " " + LocalTime.parse("11:12:31"));

        /*
         * @desc   : 　示例2： 日期和字符串的相互转换
         * @since  : 2021/2/9 9:57
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 日期时间转字符串
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        System.out.println("日期时间转字符串 nowText=" + nowText);

        // 字符串转日期时间
        String datetimeText = "1999-12-31 23:59:59";
        LocalDateTime datetime = LocalDateTime.parse(datetimeText, formatter);
        System.out.println(" 字符串转日期时间 datetime " + datetime);

        /*
         时区
         */
        // 上海时间
        ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");
        ZonedDateTime shanghaiZonedDateTime = ZonedDateTime.now(shanghaiZoneId);

        // 东京时间
        ZoneId tokyoZoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime tokyoZonedDateTime = ZonedDateTime.now(tokyoZoneId);

        System.out.println("上海时间: " + shanghaiZonedDateTime.format(formatter));
        System.out.println("东京时间: " + tokyoZonedDateTime.format(formatter));


        /*
            2021年2月9日 09:58:
            时间比较
         */
        LocalDateTime date1 = LocalDateTime.of(2000, 1, 1, 22, 11, 11);
        if (now.isAfter(date1)) {
            System.out.println("千禧年已经过去了");
        }

        LocalDateTime date2 = LocalDateTime.of(2020, 1, 1, 1, 2, 3);
        if (now.isBefore(date2)) {
            System.out.println("2020年还未到来");
        }

    }
}
