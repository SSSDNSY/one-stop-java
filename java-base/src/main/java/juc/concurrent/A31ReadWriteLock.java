package juc.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ：pengzh
 * @date ：Created in 2020/1/30 21:25
 * @description：Java的读写锁类
 * @modified By：
 */
public class A31ReadWriteLock {

    private volatile Map cache = new HashMap<>();
    private  ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String k,Object v){
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+">>>正在写入缓存:"+k);
            cache.put(k,v);
            TimeUnit.MICROSECONDS.sleep(1500);
            System.out.println(Thread.currentThread().getName()+"写入缓存完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public Object get(String k){
        rwLock.readLock().lock();
        Object o = null;
        try {
            System.out.println(Thread.currentThread().getName()+"<<<正在读取缓存"+k);
            TimeUnit.MICROSECONDS.sleep(1000);
            o = cache.get(k);
            System.out.println(Thread.currentThread().getName()+"读取缓存完成:"+o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
        return o;
    }

    public static void main(String[] args) throws InterruptedException {
       // final A31ReadWriteLock  rw = new A31ReadWriteLock();
       //  for ( int i = 1; i < 5; i++) {
       //      final int temp = i;
       //      new Thread(()->{
       //          rw.put("k"+temp,temp);
       //      },"W-"+i).start();
       //  }
       //
       //  for (int i = 1; i < 5; i++) {
       //      final int temp = i;
       //      new Thread(()->{
       //          rw.get("k"+temp);
       //      },"R-"+i).start();
       //  }
       //  Thread.currentThread().join();

        ReentrantReadWriteLock rrwLock = new ReentrantReadWriteLock();
        ReadThread rt1 = new ReadThread("rt1", rrwLock);
        ReadThread rt2 = new ReadThread("rt2", rrwLock);
        WriteThread wt1 = new WriteThread("wt1", rrwLock);
        rt1.start();
        rt2.start();
        wt1.start();

    }

}



class ReadThread extends Thread {
    private ReentrantReadWriteLock rrwLock;

    public ReadThread(String name, ReentrantReadWriteLock rrwLock) {
        super(name);
        this.rrwLock = rrwLock;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " trying to lock");
        try {
            rrwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " lock successfully");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rrwLock.readLock().unlock();
            System.out.println(Thread.currentThread().getName() + " unlock successfully");
        }
    }
}

class WriteThread extends Thread {
    private ReentrantReadWriteLock rrwLock;

    public WriteThread(String name, ReentrantReadWriteLock rrwLock) {
        super(name);
        this.rrwLock = rrwLock;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " trying to lock");
        try {
            rrwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " lock successfully");
        } finally {
            rrwLock.writeLock().unlock();
            System.out.println(Thread.currentThread().getName() + " unlock successfully");
        }
    }
}
