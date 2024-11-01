package juc.concurrent2;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * 多线程的顺序打印问题
 * 1 LockSupport
 * 2 cas volatile
 * 3 AtomicInteger
 * 4 wait notify
 *
 * @Description
 * @Since 2020-09-26
 */
public class PrintInTurns {

    char[] chars = new char[]{'A', 'B', 'C'};
    int[] ints = new int[]{1, 2, 3};

    enum turn {th1, th2}

    turn t = turn.th1;

    static AtomicBoolean run = new AtomicBoolean(true);

    static Thread t1 = null;
    static Thread t2 = null;


    @Test
    public void lockSupport() {

        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (char c : chars) {
                    System.out.print(c);
                    sleep(300);
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
                System.out.println();
            }
        }, "字母");
        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i : ints) {
                    LockSupport.park();
                    System.out.print(i);
                    sleep(300);
                    LockSupport.unpark(t1);
                }
                System.out.println();
            }
        }, "数字");

        System.out.println();
        t1.start();
        t2.start();
        sleep(3000);
    }

    @Test
    public void casWithEnum() {
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (char c : chars) {
                    while (t != turn.th1) {
                    }
                    System.out.print(c);
                    t = turn.th2;
                }
            }
        }, "字母");
        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i : ints) {
                    while (t != turn.th2) {
                    }
                    System.out.print(i);
                    t = turn.th1;
                }
            }
        }, "数字");

        System.out.println();
        t1.start();
        t2.start();
        sleep(1);
    }

    @Test
    public void atomicHelp() {
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (char c : chars) {
                    while (!run.get()) {
                    }
                    System.out.print(c);
                    run.set(false);
                }
            }
        }, "字母");
        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i : ints) {
                    while (run.get()) {
                    }
                    System.out.print(i);
                    run.set(true);
                }
                System.out.println();
            }
        }, "数字");
        System.out.println();
        t1.start();
        t2.start();
//        sleep(1000);
    }

    @Test
    public void waitNotify() {

        Object lock = new Object();
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (char c : chars) {
                        try {
                            System.out.print(c);
                            lock.notifyAll();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println();
            }
        }, "字母");
        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i : ints) {
                    synchronized (lock) {
                        try {
                            lock.notifyAll();
                            lock.wait();
                            System.out.print(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println();
            }
        }, "数字");

        System.out.println();
        t1.start();
        t2.start();
        sleep(10000);
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
