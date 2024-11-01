package juc.concurrent2;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author pengzh
 * @desc
 * @class ThreadLocalMemoryLeakDemo
 * @since 2022-02-10
 */
public class Thread_10_ThreadLocalMemoryLeakDemo {

    private static class Variables {
        private long[] longArray = new long[1024 * 1024];
    }

    final static ThreadPoolExecutor pool = new ThreadPoolExecutor(100, 100, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

    final static ThreadLocal<Variables> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 500; i++) {
            Thread.sleep(30);
            pool.execute(() -> {
                threadLocal.set(new Variables());
                System.out.println("user threadLocal get" + threadLocal.get());
                // remove与否 内存泄露
//                threadLocal.remove();
            });
        }
//        pool.shutdown();
        System.out.println("pool execute over.");


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    TestClass t = new TestClass(i);
                    t.printId();
                    t = null;
                }
            }
        }).start();
    }


    /**
     * 代码出处来源于Stack Overflow：https://stackoverflow.com/questions/17968803/threadlocal-memory-leak
     */
    static class TestClass {
        private int id;
        private int[] arr;
        private ThreadLocal<TestClass> threadLocal;

        TestClass(int id) {
            this.id = id;
            arr = new int[1000000];
            threadLocal = new ThreadLocal<>();
            threadLocal.set(this);
        }

        public void printId() {
            System.out.println(threadLocal.get().id);
        }
    }

}
