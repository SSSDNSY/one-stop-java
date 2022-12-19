package sssdnsy.cjuc.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/2 10:55
 * @description： 第三种线程的实现方法
 * @modified By：
 */
public class A45Callable implements Callable<Integer>{


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new A45Callable());
        new Thread(futureTask,"AA").start();
        int a = (int) futureTask.get();
        while (!futureTask.isDone()){

        }
//        int a = (int) futureTask.get();//获取callable接口的计算接口 ，没有计算完成会导致阻塞
        int b = 1;
        System.out.println("result="+(a+b));
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("come in ...");
        return 1024;
    }
}
