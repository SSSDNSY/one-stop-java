package sssdnsy.cjuc.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/1 8:09
 * @description：生产消费线程模型
 * 一个初始值为0的变量，多线程轮流共5轮加减1
 * sync notify wait
 * lock await  signalAll
 * @modified By：
 */
public class A41ProdConsumer {
    public static void main(String[] args) {
        final ShareData sd = new ShareData();
        //++

            new Thread(()->{
                for (int i = 0; i < 5; i++) {
                    sd.increase();
                }
            },"AA").start();

        //--

            new Thread(()->{
                for (int i = 0; i < 5; i++) {
                sd.decrease();
                }
            },"BBB").start();

    }
}

class ShareData{
    private int number = 0;
    private Lock lock =  new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increase()  {
        lock.lock();
        try {
            while (number!=0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrease(){
        lock.lock();
        try {
            while (number==0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
