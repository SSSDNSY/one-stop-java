package lang.cjuc.concurrent2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pengzh
 * @since 2020-06-30
 */
public class ThreadUnsafeExample {
    private volatile int count;

    public int getCount() {
        return count;
    }

    public void addCount() {
        this.count++;
    }

    public static void main(String[] args) throws Exception {
        int threadSize = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        final ThreadUnsafeExample example = new ThreadUnsafeExample();
        ExecutorService executors = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            countDownLatch.countDown();

            executors.submit(new Thread(new Runnable() {
                @Override
                public void run() {
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
