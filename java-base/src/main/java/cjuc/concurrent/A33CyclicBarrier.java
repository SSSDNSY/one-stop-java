package cjuc.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ：pengzh
 * @date ：Created in 2020/1/31 10:57
 * @description：A synchronization aid that allows a set of threads to all wait for each other to reach a common barrier point
 * @modified By：
 */
public class A33CyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("*********召唤神龙！");
        });

        for (int i = 1; i < 8; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "集齐到了第" + temp + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, i + "").start();
        }
    }
}
