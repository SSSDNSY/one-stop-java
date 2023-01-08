package juc.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ConcurrentDemoSynchronized extends Thread{

    private static int SIZE = 1000;
    CyclicBarrier cyclicBarrier;

    public ConcurrentDemoSynchronized(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 准备就绪");
        try {
            SIZE--;
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Start SIZE=" + SIZE);

        /**
         * synchnized 关键字：保证一个CPU时刻只有一个线程能获得对象锁
         * 锁竞争：当一个线程获得对象锁之后，其他线程一直不断在竞争该对象的锁，
         *        如果竞争的线程数量达到一定的级别之后，就会导致高CPU占用率。
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(SIZE);
        ConcurrentDemoSynchronized c = new ConcurrentDemoSynchronized(cyclicBarrier);

        for (int i = 1; i <= SIZE; i++) {
            new Thread(c, "t" + i).start();
        }
        System.out.println("End SIZE=" + SIZE);

    }


}
