package juc.concurrent.concurrentUtils;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
*@Description: 信号量
*@Author: pengzh
*@date: 2019/9/14
*/
public class UseSemaphore {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //20个请求 只能并发5个
        Semaphore semaphore = new Semaphore(5);
        for(int idx = 0;idx<20;idx++){
            final  int i = idx;
            Runnable t1  = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("任务"+i);
                        Thread.sleep(new Random().nextInt(4321));
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.submit(t1);
        }
        executorService.shutdown();
    }
}
