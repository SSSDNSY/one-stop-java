package cjuc.concurrent2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pengzh
 * @since 2020-06-30
 */
public class Thread_12_ThreadUnsafeExample {
    private  int count;

    public  int getCount() {
        return count;
    }

    public  void addCount() {
        this.count++;
    }

    public static void main(String[] args) throws Exception {
        int threadSize = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        final Thread_12_ThreadUnsafeExample example = new Thread_12_ThreadUnsafeExample();
        ExecutorService executors = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {

            executors.submit(new Thread(new Runnable() {
                @Override
                public void run() {
                    countDownLatch.countDown();
                        example.addCount();
                }
            }));
        }
        countDownLatch.await();
        executors.shutdown();
        System.out.println();
        System.out.println(example.getCount());
    }
}
