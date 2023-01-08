package juc.concurrent.threadpools;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Provider implements Runnable {

    private LinkedBlockingQueue q;
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private volatile boolean isRunning = true;

    public Provider(LinkedBlockingQueue q) {
        this.q = q;
    }

    @Override
    public void run() {
        Random r = new Random();
        while (isRunning) {
            try {
                int id = atomicInteger.addAndGet(1);
                Data d = new Data(id, "name" + id);
                Thread.sleep(r.nextInt(1000));
                System.out.println("----------"+Thread.currentThread().getName()+" 生产了数据:"+d);
                if (!q.offer(d, 2, TimeUnit.SECONDS)) {
                    System.out.println("生产数据失败");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.isRunning = false;
    }
}
