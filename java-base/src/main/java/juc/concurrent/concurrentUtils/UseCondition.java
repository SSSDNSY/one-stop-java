package juc.concurrent.concurrentUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: condition 类似Object类里面的 wati notify/notifyAll
 * @Author: pengzh
 * @date: 2019/9/15
 */
public class UseCondition {

    private Lock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();
    public void m1() {
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+"进入m1");
            System.out.println(Thread.currentThread().getName()+"等待");
            condition.await();
            Thread.sleep(1900);
            System.out.println(Thread.currentThread().getName()+"继续执行");
        } catch (Exception e) {

        } finally {
            reentrantLock.unlock();
            System.out.println("m1释放锁\n");
        }
    }

    public void m2() {
        try {
            System.out.println(Thread.currentThread().getName()+"进入m2");
            reentrantLock.lock();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"发出信号");
            condition.signal();

        } catch (Exception e) {

        } finally {
            System.out.println("m2释放锁\n");
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        UseCondition uc = new UseCondition();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                uc.m1();
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                uc.m2();
            }
        },"t2");
        t1.start();
        t2.start();
    }
}
