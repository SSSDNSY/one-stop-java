package lang.cjuc.concurrent.concurrentUtils;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 读写锁  读读共享  有写互斥
 * @Author: pengzh
 * @date: 2019/9/15
 */
public class UseReadWriteLock {

    private ReentrantReadWriteLock rrw = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = rrw.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = rrw.writeLock();

    public void readM() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " 读取...");
            Thread.sleep(600);
        } catch (Exception e) {

        } finally {
            System.out.println(Thread.currentThread().getName() + " 读取完成、释放锁...");
            readLock.unlock();
        }
    }

    public void writeM() {
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " 写入...");
            Thread.sleep(1500);

        } catch (Exception e) {

        } finally {
            System.out.println(Thread.currentThread().getName() + " 写入完成、释放锁...");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {

        UseReadWriteLock urwl = new UseReadWriteLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                urwl.readM();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                urwl.readM();
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                urwl.writeM();
            }
        }, "t3");

        t1.start();//读取
        t2.start();//读取
        t3.start();//写入

    }
}
