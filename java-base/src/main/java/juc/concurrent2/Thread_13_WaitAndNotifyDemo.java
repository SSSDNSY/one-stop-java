package juc.concurrent2;

import java.util.concurrent.TimeUnit;

/**
 * @author fun.pengzh
 * @class sssdnsy.cjuc.concurrent2.WaitAndNotifyDemo
 * @desc
 * @since 2020-11-26
 */
public class Thread_13_WaitAndNotifyDemo {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        TimeUnit.SECONDS.sleep(2);
        synchronized (myThread) {
            //主线程阻塞
            System.out.println("before wait");
            //主线程释放锁
            myThread.wait();
            System.out.println("after wait");
        }
    }
}


class MyThread extends Thread {

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("before noityf");
            notify();
            System.out.println("after noityf");
        }
    }
}
