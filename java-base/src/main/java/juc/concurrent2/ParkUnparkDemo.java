package juc.concurrent2;

import java.util.concurrent.locks.LockSupport;

/**
 * @class sssdnsy.cjuc.concurrent2.ParkUnparkDemo
 * @desc W:\code\algorithm\gh\src\main\java\lang\cjuc\concurrent2\WaitAndNotifyDemo.java
 * W:\code\algorithm\gh\src\main\java\lang\cjuc\concurrent2\java-thread-x-locksupport-1.png
 * @since 2020-11-26
 */
public class ParkUnparkDemo {
    public static void main(String[] args) {
        final Mythread myThread = new Mythread(Thread.currentThread());
        myThread.start();
        System.out.println("before park");
        LockSupport.park();
        System.out.println("after park");
    }
}


class Mythread extends Thread {
    private Object object;

    public Mythread(Object o) {
        this.object = o;
    }

    @Override
    public void run() {

        System.out.println("before unpark");
        LockSupport.unpark((Thread) object);
        System.out.println("after unpark");

    }

}
