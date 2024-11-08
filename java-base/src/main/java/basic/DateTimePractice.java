package basic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

/**
 * @Desc 日期时间
 * @Since 2023-05-18
 */
public class DateTimePractice {


    private static void test1() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        System.out.println(year);
        System.out.println(month);
    }

    private static void test2() {
        //LocalDateTime 获取当前月
        System.out.println(LocalDateTime.now().getYear());
        System.out.println(LocalDateTime.now().getMonth().getValue());
    }

    private static void test3() {

        LocalDate date = LocalDate.now();
        LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfMonth()); //LocalDateTime 获取当前月的第一天
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth()); // 获取当前月的最后一天
        date.minusMonths(1);
        System.out.println(date);
        System.out.println(firstDay);
        System.out.println(lastDay);
    }

    /**
     * 获取上个月的第一天和最后一天
     */
    private static void test4() {

        LocalDate date = LocalDate.now();
        LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth()); // 获取当前月的最后一天
        System.out.println(date);
        System.out.println(firstDay);
        System.out.println(lastDay);
        date.minusMonths(1);

        firstDay = date.with(TemporalAdjusters.firstDayOfMonth());
        lastDay = date.with(TemporalAdjusters.lastDayOfMonth()); // 获取当前月的最后一天
        System.out.println(date);
        System.out.println(firstDay);
        System.out.println(lastDay);

        System.out.println(LocalDateTime.of(2023, 12, 1, 0, 0, 0));
    }


    public static void main(String[] args) {
        test4();
    }
}
