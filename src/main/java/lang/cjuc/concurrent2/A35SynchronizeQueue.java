package lang.cjuc.concurrent2;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author ：pengzh
 * @date ：Created in 2020/1/31 11:08
 * @description：同步队列
 * @modified By：
 */
public class A35SynchronizeQueue {
    public static void main(String[] args) {
        SynchronousQueue sq = new SynchronousQueue();
        new Thread(()->{
            try {
                System.out.println("1");
                sq.put(1);

                System.out.println("2");
                sq.put(2);

                System.out.println("3");
                sq.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("take "+sq.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("take "+sq.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("take "+sq.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
