package cjuc.concurrent.concurrentUtils;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
*@Description:  发令枪  集齐了才能出发  做加法
*@Author: pengzh
*@date: 2019/9/14
*/
public class UseCyclicBarrier implements  Runnable {
    private String name;
    private CyclicBarrier cyclicBarrier;

    public void setName(String name) {
        this.name = name;
    }
    public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public UseCyclicBarrier(String name, CyclicBarrier cyclicBarrier) {
        this.name = name;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println(this.name+"准备就绪");
        try {
            Thread.sleep(new Random().nextInt(1000));
            this.cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(this.name+"GO");
    }
    public static void main(String[] args) {
        ExecutorService executorService =Executors.newFixedThreadPool(3);
        CyclicBarrier cb = new CyclicBarrier(3);
        executorService.submit(new UseCyclicBarrier("张三",cb));
        executorService.submit(new UseCyclicBarrier("李四",cb));
        executorService.submit(new UseCyclicBarrier("王五",cb));
        executorService.shutdown();
    }
}
