import org.junit.Test;
import utils.TimeTool;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author pengzh
 * @date 2020-03-23
 */
public class UnitTest {
    @Test
    public void testJavaFloat() {
        float x = 0.3f - 0.2f;
        float y = 0.2f - 0.1f;
        System.out.println(x);
        System.out.println(y);
        System.out.println(x == y);
    }

    @Test
    public void testRandom() {
        System.out.println(new Random().nextInt(999999));
        String pwd = "";
        for (int i = 1; i <= 6; i++) {
            pwd += new Random().nextInt(10);
        }
        System.out.println(pwd);
    }

    @Test
    public void testTimeUtil() {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(TimeTool.getFirstDayMonth(LocalDate.now()));
        System.out.println(Long.parseLong(TimeTool.format(TimeTool.getFirstDayMonth(LocalDate.now()), TimeTool.DATE_FMT_0)));
        System.out.println(Long.getLong("20501231"));
        System.out.println(Long.parseLong("20501231"));
        System.out.println(Long.decode("20501231"));
        System.out.println(TimeTool.foreverTime());
        System.out.println(Long.parseLong(TimeTool.format(TimeTool.getFirstDayMonth(LocalDate.now()), TimeTool.DATE_FMT_0)));
//        System.out.println(Long.parseLong(TimeTool.foreverTime().toString()));
        System.out.println(Long.parseLong(TimeTool.format(TimeTool.getLastDayMonth(LocalDate.now()), TimeTool.DATE_FMT_0)));

    }
    @Test
    public void TestEquals(){
        String strVal = String.valueOf(1-1);
        System.out.println(strVal);
        System.out.println("0" == strVal);
    }
}

