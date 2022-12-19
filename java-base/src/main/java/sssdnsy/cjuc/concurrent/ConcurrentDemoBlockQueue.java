package sssdnsy.cjuc.concurrent;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentDemoBlockQueue {

    private LinkedList<Object> queue = new LinkedList<>();

    private AtomicInteger counter = new AtomicInteger(0);

    private int minSize = 0;

    private int maxSize;

    Object lockObj = new Object();

    public ConcurrentDemoBlockQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void put(Object obj) {
        synchronized (lockObj) {
            while (counter.get() == maxSize) {
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add(obj);
            System.out.println(Thread.currentThread().getName() + "放入对象：" + obj+",   queue.size()="+counter.get());
            lockObj.notifyAll();
            counter.incrementAndGet();
        }
    }

    public Object take() {
        Object obj = null;
        synchronized (lockObj) {
            while (counter.get() == 0) {
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            obj = queue.getLast();
            System.out.println(Thread.currentThread().getName() + "取出对象：" + obj);
            lockObj.notifyAll();
            counter.decrementAndGet();
        }
        return obj;
    }

    public static void main(String[] args) {
        ConcurrentDemoBlockQueue cdbq = new ConcurrentDemoBlockQueue(5);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 10){
                    cdbq.put("obj"+i);
                    i++;
                }
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 20){
                    Object obj = cdbq.take();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(obj);
                    i++;
                }
            }
        },"t2");
        t1.start();
        t2.start();
    }

}
