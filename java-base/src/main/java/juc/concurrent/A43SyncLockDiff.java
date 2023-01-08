package juc.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/1 8:09
 * @description：生产消费线程模型
 *synchronize和lock的异同
 *  1、原始构造：
 *  synchronize是JVM底层通过monitor对象的实现，而lock则是JDK的JUC的对象，属于API实现的锁
 *  2、使用方法
 *  synchronize不需要用户手动去释放锁，而ReentrantLock必须要释放锁 并且lock和unlock需要配合 try catch finally 语句来使用
 *  3、中断等待
 *  synchronize不能中断，除非正常执行完成或者抛出异常，ReentrantLock有超时中断和interruptibly()代码快中通过interrupt()中断
 *  4、加锁公平
 *  synchronize是非公平锁，而ReentrantLock默认是非公平锁，但是可以通过构造方法设置成公平锁
 *  5、锁的条件
 *  synchronize没有条件，而ReentrantLock 有多condition可以实现精确唤醒
 *
 *  轮流打印实例：一轮循环中，A打印5次B打印10次C打印15次，然后再次循环次流程，共10轮。
 */
public class A43SyncLockDiff {
    public static void main(String[] args) {
        ShareRes sr = new ShareRes();
        new Thread(()->{
            for(int idx = 0; idx < 10; idx++) {
                sr.Print5();
            }
        },"A").start();
        new Thread(()->{
            for(int idx = 0; idx < 10; idx++) {
                sr.Print10();
            }
        },"B").start();
        new Thread(()->{
            for(int idx = 0; idx < 10; idx++) {
                sr.Print15();
            }
        },"C").start();
    }
}

class ShareRes{
    //判定标识
    private int number = 1;
    private Lock lock =  new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void Print5(){
        lock.lock();
        try {
            while(number!=1){
                c1.await();
            }
            number=2;

            for (int i = 0; i < 5; i++) {
                System.out.print(Thread.currentThread().getName()+i+"\t");
            }
            System.out.println();
            c2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void Print10(){
        lock.lock();
        try {
            while (number!=2){
                c2.await();
            }
            number=3;
            for(int idx = 0; idx < 10; idx++) {
                System.out.print(Thread.currentThread().getName()+idx+"\t");
            }
            System.out.println();
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void Print15(){
        lock.lock();
        try {
            while (number!=3){
                c3.await();
            }
            number=1;
            for(int idx = 0; idx < 15; idx++) {
                System.out.print(Thread.currentThread().getName()+idx+"\t");
            }
            System.out.println();
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
