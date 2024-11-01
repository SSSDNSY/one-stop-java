package features.annotation;

import java.util.Date;

/**
 * @author pengzh
 * @date 2020-03-10
 */
public class SuppressWarningTest {
    //    @SuppressWarnings(value={"deprecation"})
    public static void doSomething() {
        Date date = new Date(113, 8, 26);
        System.out.println(date);
    }

    public static void main(String[] args) {
        doSomething();
    }
}
