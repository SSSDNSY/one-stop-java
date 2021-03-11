package lang.cjuc.concurrent.concurrentUtils;

import java.util.concurrent.CountDownLatch;


/**
*@Description:  wait notify   某线程等待其他线程初始化完成才继续执行
*@Author: pengzh 做减法 全部怎么 了才能怎么
*@date: 2019/9/14
*/
public class UseCountDown {

    public static void main(String[] args) {
        final CountDownLatch countDown = new CountDownLatch(2);//给定了几次 就需要用几次 countDonw()方法
        new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("进入T1线程，等待其他线程完成再执行、、、");
                try {
                    countDown.await();
                    System.out.println("              T2,T3初始化完成、再次执行T1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T1").start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("T2初始化开始、、、");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T2线程初始化完成，通知T1");
                countDown.countDown();
            }/////
        },"T2").start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("T3初始化开始、、、");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T3线程初始化完成，通知T1");
                countDown.countDown();
            }
        },"T3").start();

    }
}
