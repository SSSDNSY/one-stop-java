package juc.concurrent;

public class ConcurrentDemoSynchronized3 {

   synchronized void method1(){
       System.out.println(Thread.currentThread().getName());
       try {
           Thread.sleep(3*1000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

   }
   //synchronized 不加就是异步方法，加了就是同步方法，同一个对象只有一把对象锁  t2必须等t1线程执行method1 完毕才能执行method2
   synchronized void method2(){
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ConcurrentDemoSynchronized3 obj = new ConcurrentDemoSynchronized3();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                obj.method1();
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                obj.method2();
            }
        },"t2");
        t1.start();
        t2.start();

    }

}
