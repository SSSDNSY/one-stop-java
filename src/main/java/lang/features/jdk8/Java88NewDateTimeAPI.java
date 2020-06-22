package lang.features.jdk8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author pengzh
 * @date 2020-03-03
 */
public class Java88NewDateTimeAPI {
    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("当前时间"+ldt);

        System.out.println(ldt.toLocalDate());
        System.out.println(LocalDate.parse("2020-11-11")+" "+ LocalTime.parse("11:12:31"));
    }
}
