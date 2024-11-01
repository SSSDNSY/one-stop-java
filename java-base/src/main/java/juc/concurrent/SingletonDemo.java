package juc.concurrent;

/**
 * @author imi
 */
public class SingletonDemo {

    private static SingletonDemo ins = null;

    private SingletonDemo() {
        System.out.println("SingletonDemo构造方法执行");
    }
    //1不加 synchronized 多线程并发环境下导致非单例,但是synchronized太重了，锁住了整个方法降低了并发性？

    public static SingletonDemo getInstance() {
        if (ins == null) {
            ins = new SingletonDemo();
        }
        return ins;
    }

    //2 DCL double check lock
    public static SingletonDemo getInstance2() {
        if (ins == null) {
            synchronized (SingletonDemo.class) {
                if (ins == null) {
                    ins = new SingletonDemo();
                }
            }
        }
        return ins;
    }

    public static void main(String[] args) {
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        for (int i = 0; i < 99; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SingletonDemo.getInstance2();
                }
            }, String.valueOf(i)).start();
        }
    }
}
