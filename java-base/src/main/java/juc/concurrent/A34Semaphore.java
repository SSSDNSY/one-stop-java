package juc.concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author ：pengzh
 * @date ：Created in 2020/1/31 11:08
 * @description：信号量
 * @modified By：
 */
public class A34Semaphore {
    public static void main(String[] args) {

        releaseProblem(args);

        Semaphore semaphore = new Semaphore(3, false);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到了车位");
                    int randNum = new Random().nextInt(5);
                    TimeUnit.SECONDS.sleep(randNum);
                    System.out.println("\t\t" + Thread.currentThread().getName() + "停留" + randNum + "秒后离开。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }, i + "号车子 ").start();
        }
    }

    public static void releaseProblem(String[] args) {
        int permitsNum = 2;
        final Semaphore semaphore = new Semaphore(permitsNum);
        try {
            System.out.println("availablePermits:" + semaphore.availablePermits() + ",semaphore.tryAcquire(3,1, TimeUnit.SECONDS):" + semaphore.tryAcquire(3, 1, TimeUnit.SECONDS));
            semaphore.release();
            System.out.println("availablePermits:" + semaphore.availablePermits() + ",semaphore.tryAcquire(3,1, TimeUnit.SECONDS):" + semaphore.tryAcquire(3, 1, TimeUnit.SECONDS));
        } catch (Exception e) {

        }
    }
}
