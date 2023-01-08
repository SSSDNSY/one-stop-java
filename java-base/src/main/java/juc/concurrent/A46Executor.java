package juc.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/2 11:28
 * @description： 线程池 (第四种Java 实现多线程的方式)
 * @modified By：
 */
public class A46Executor {
    public static void main(String[] args) {
        //固定5线程
        ExecutorService es1 = Executors.newFixedThreadPool(5);
        //自扩容线程池
        ExecutorService es2 = Executors.newCachedThreadPool();
        //单个线程的
        ExecutorService es3 = Executors.newSingleThreadExecutor();

        //useFixed
        for(int idx = 1; idx < 10; idx++) {
            es3.execute(()->{
                System.out.println(Thread.currentThread().getName()+"\t办理业务");
            });
        }
    }
}
