package lang.cjuc.concurrent2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author fun.pengzh
 * @class lang.cjuc.concurrent2.ThreadLocalDemo2
 * @desc TODO to resolve error
 * @since 2021-03-09
 */
public class ThreadLocalDemo2 {


    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    public static void main(String[] args) throws Exception{

        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 1000; i++) {
            executorService.submit(()->{
                 Date date = null;
                try {
                    date = simpleDateFormat.parse("2020-08-09");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println(date);
            });
        }
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdown();
    }

}
