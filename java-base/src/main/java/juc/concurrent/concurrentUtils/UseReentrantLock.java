package juc.concurrent.concurrentUtils;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: synchronized reentrantLock都是典型的可重入锁
 * @Author: pengzh
 * @date: 2019/9/15
 */
public class UseReentrantLock {

    ReentrantLock reentrantLock = new ReentrantLock();

    public void m1() {
        try {
            System.out.println(Thread.currentThread().getName() + "线程进入m1()");
            reentrantLock.lock();
            reentrantLock.lock();
            Thread.sleep(1200);
            System.out.println(Thread.currentThread().getName() + "线程退出m1()");
        } catch (Exception e) {

        } finally {
            reentrantLock.unlock();
        }

    }

    public void m2() {
        try {
            System.out.println(Thread.currentThread().getName() + "线程进入m2()");
            reentrantLock.lock();
            Thread.sleep(1200);
            System.out.println(Thread.currentThread().getName() + "线程退出m2()");
        } catch (Exception e) {

        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        UseReentrantLock urtl = new UseReentrantLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                urtl.m1();
                urtl.m2();
            }
        }, "T1");
        t1.start();
        new Thread(()->{
            new phone().sendSMS();
        },"t2").start();
        new Thread(()->{
            new phone().sendSMS();
        },"t3").start();
    }
}

class phone {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getId() + " :发送短信！");
        sendEmail();
    }
    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getId() + " :发送邮件！");
    }
}
