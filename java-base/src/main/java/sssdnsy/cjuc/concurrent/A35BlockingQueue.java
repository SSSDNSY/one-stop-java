package sssdnsy.cjuc.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author ：pengzh
 * @date ：Created in 2020/1/31 11:08
 * @description：阻塞队列
 * @modified By：
 */
public class A35BlockingQueue {
    public static void main(String[] args) {
        BlockingQueue bq = new ArrayBlockingQueue(3);
        bq.add(1);
        bq.add(1);
        System.out.println(bq.add(1));

        //Exception in thread "main" java.lang.IllegalStateException: Queue full
        bq.add(1);
    }
}
