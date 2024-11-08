package juc.concurrent2;

import static java.lang.Thread.interrupted;

/**
 * W:\code\algorithm\gh\src\main\java\lang\cjuc\concurrent2\java-thread-x-locksupport-1.png
 *
 * @since 2020-07-02
 */
public class InterruptedExceptionDemo {
    public static void main(String[] args) throws InterruptedException {
        //1 、sleep会被打断
//        interruptSleep();

        //1、死循环打断不了
        interruptDeadloop();
    }

    private static void interruptSleep() {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("thread run sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t1.interrupt();
        System.out.println("main run");
    }

    private static void interruptDeadloop() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (!interrupted()) {
                System.out.println(interrupted());
            }
            System.out.println(" interrupted");

        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
        System.out.println("main run");
    }

}
