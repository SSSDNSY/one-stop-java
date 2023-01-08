package juc.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join框架主要包含三个模块:
 * 任务对象: ForkJoinTask (包括RecursiveTask、RecursiveAction 和 CountedCompleter)
 * 执行Fork/Join任务的线程: ForkJoinWorkerThread
 * 线程池: ForkJoinPool
 * <p>
 * fork / join
 * 斐波那契数列: 1、1、2、3、5、8、13、21、34、…… 公式 : F(1)=1，F(2)=1, F(n)=F(n-1)+F(n-2)(n>=3，n∈N*)
 *
 * @author pengzh
 * @since 2020-06-22
 */
public class A45ForkJoin {


    private static final int SIZE = 999;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask task = new SumTask(1, 99999);
        pool.submit(task);
        System.out.println("获取计算结果:sum=" + task.get());
        pool.shutdownNow();
        System.out.println("\n------------------------------------------------------------------------------------------\n");

        System.out.println("1、1、2、3、5、8、13、21、34......");
        ForkJoinPool pool2 = new ForkJoinPool(2);
        final long l1 = System.currentTimeMillis();
        for (int i = 1; i < SIZE; i++) {
            FibonacciTask forkJoinTask = new FibonacciTask(i);
            Integer result = pool2.invoke(forkJoinTask);
            System.out.print(result + "、");
        }
        System.out.println("\nFork/join Fibonacci sum:  in " + (System.currentTimeMillis() - l1) + " ms.");

        final long l2 = System.currentTimeMillis();
        for (int i = 1; i < SIZE; i++) {
            System.out.print(traditionalFibonacci(i) + "、");
        }
        System.out.println("\nTtraditionalFibonacci sum:  in " + (System.currentTimeMillis() - l2) + " ms.");


    }

    public static int traditionalFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return traditionalFibonacci(n - 2) + traditionalFibonacci(n - 1);
        }
    }


}

/**
 * 累加 fork / join 框架计算 1+2+3+...+n
 *
 * @author : pengzh
 * @since : 2020/6/22 10:47
 */
class SumTask extends RecursiveTask<Integer> {
    int start, end;


    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (end - start < 100) {
            for (int i = start; i <= end; i++) {
                result += i;
            }
            return result;
        } else {
            SumTask tast1 = new SumTask(start, (start + end) / 2);
            SumTask tast2 = new SumTask((start + end) / 2 + 1, end);
            tast1.fork();
            tast2.fork();
            return tast1.join() + tast2.join();
        }
    }
}

/**
 * 计算斐波那契数列
 */
class FibonacciTask extends RecursiveTask<Integer> {
    int n;

    public FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        FibonacciTask n1 = new FibonacciTask(n - 1);
        n1.fork();
        FibonacciTask n2 = new FibonacciTask(n - 2);
        return n2.compute() + n1.join();
    }

    protected Integer compute2() {
        if (n <= 1) {
            return n;
        }
        FibonacciTask f1 = new FibonacciTask(n - 1);
        f1.fork();
        FibonacciTask f2 = new FibonacciTask(n - 2);
        return f2.compute() + f1.join();
    }

    protected Integer compute3() {
        if (n <= 1) {
            return n;
        }
        FibonacciTask f1 = new FibonacciTask(n - 1);
        FibonacciTask f2 = new FibonacciTask(n - 2);
        invokeAll(f1, f2);
        return f2.join() + f1.join();
    }
}
