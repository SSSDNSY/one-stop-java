package sssdnsy.cjuc.concurrent;

import java.util.concurrent.*;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/2 20:34
 * @description： 线程池
 * 参数如下注释
 * 工作原理：核心线程满么？ 等待队列？最大线程数到了？ 执行拒绝策略
 * 拒绝策略4
 * 默认 AbortPolicy 抛出RejectedExecutionException 阻止系统正常运行
 * CallerRunsPolicy 回退给调用者
 * DiscardOldestPolicy 抛弃队列中等待最久的任务
 * DiscardPolicy 直接抛弃任务
 *
 * 设置线程数和队列容量
 * 根据业务是CPU密集型还是IO密集型
 * 如果是CPU密集型 建议是 CPU核心数+1个线程的线程数
 * IO型的建议是 CPU核心数*2 ，参考公式 ： CPU核心数/ 1-r  （阻塞系数 r=08~0.9） 8-/(1-0.9)=80
 *
 * @modified By：
 */
public class A47ThreadPoolExecutor {
    public static void main(String[] args) {
        System.out.println("CPU核心数="+Runtime.getRuntime().availableProcessors());
        //爆CPU

        ExecutorService es = new ThreadPoolExecutor(2, //核心线程数
                5, //最大线程数 Integer.MAX_VALUE 容易导致OOM
                1L,//多余线程保活时间
                TimeUnit.MILLISECONDS,//keepAliveTime的单位
                new LinkedBlockingDeque<Runnable>(3),//任务队列
                Executors.defaultThreadFactory(),//默认线程工程
//                new ThreadPoolExecutor.AbortPolicy());//拒绝策略
//                new ThreadPoolExecutor.CallerRunsPolicy());//回退策略
//                new ThreadPoolExecutor.DiscardOldestPolicy());//最长舍弃策略
                new ThreadPoolExecutor.DiscardPolicy());//直接舍弃策略
//
        try {
            //模拟10个用户来办理业务
            for (int i = 0; i < 100; i++) {
                es.execute(() -> {

                    System.out.println(Thread.currentThread().getName() + "\t办理业务！");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.shutdown();
        }
    }
}
