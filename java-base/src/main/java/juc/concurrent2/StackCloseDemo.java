package juc.concurrent2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程封闭方案
 * @author pengzh
 * @since 2020-07-02
 */
public class StackCloseDemo {
    private void addVal() {
        int val = 0;
        val=val+1;
        System.out.println(val);
    }

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final StackCloseDemo demo = new StackCloseDemo();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> demo.addVal());
        }
        executorService.shutdown();
    }
}
