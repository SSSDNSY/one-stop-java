package sssdnsy.cjuc.concurrent;

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
            TimeUnit.MICROSECONDS.sleep(150);
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
            TimeUnit.MICROSECONDS.sleep(100);
            o = cache.get(k);
            System.out.println(Thread.currentThread().getName()+"读取缓存完成:"+o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
        return o;
    }

    public static void main(String[] args) {
       final A31ReadWriteLock  rw = new A31ReadWriteLock();
        for ( int i = 1; i < 5; i++) {
            final int temp = i;
            new Thread(()->{
                rw.put("k"+temp,temp);
            },"W-"+i).start();
        }

        for (int i = 1; i < 5; i++) {
            final int temp = i;
            new Thread(()->{
                rw.get("k"+temp);
            },"R-"+i).start();
        }

    }

}
