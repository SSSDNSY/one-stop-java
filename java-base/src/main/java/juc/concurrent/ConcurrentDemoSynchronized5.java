package juc.concurrent;

/**
 * synchronized 锁重入
 */
public class ConcurrentDemoSynchronized5 {

    public synchronized  void func1(){
        System.out.println("func1");
        func2();
    }
    public synchronized  void func2(){
        System.out.println("func2");
        func3();
    }
    public synchronized  void func3(){
        System.out.println("func3");
    }
    public static void main(String[] args) {
        ConcurrentDemoSynchronized5 c5 = new ConcurrentDemoSynchronized5();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                c5.func1();
            }
        },"t1");
        t1.start();

    }


}
