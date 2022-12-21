package cjuc.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 1 公平锁和非公平锁 ：按照锁申请的顺序获取锁就是公平的，非公平不按照顺序会导致优先级反转和竞争现象
 * 2 共享锁和独占锁
 * 3 可重入锁 ： 同一个线程的外层函数获取到了锁之后，内层的函数仍然能获取到锁 。也就是说线程可以进入到任何一个他已经拥有锁所同步的代码块
 * 4 自旋锁： 尝试获取锁的线程不会阻塞而是循环去获取锁，减少上下文切换但是消耗了CPU
 */
public class LockInJava {

    AtomicReference<Thread> ar = new AtomicReference<>();

    public void doLock() {
        Thread t = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " come in ");
        //自旋锁
        while (!ar.compareAndSet(null, t)) {

        }
    }

    public void doUnLock() {
        Thread t = Thread.currentThread();
        ar.compareAndSet(t, null);
        System.out.println(Thread.currentThread().getName() + " come out ");
    }

    public static void main(String[] args) throws InterruptedException {
        final LockInJava lock = new LockInJava();
        new Thread(() -> {
            lock.doLock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.doUnLock();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            lock.doLock();
            lock.doUnLock();
        }, "B").start();
    }

}
