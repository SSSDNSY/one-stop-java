package sssdnsy.cjuc.concurrent;

import java.util.Date;

/**
 * @author: imi
 * @Date: 2019/6/1 16:47
 * @Description: 熟记Thread类的各个方法
 */
public class TreadTest {

    protected static boolean RUN_FLAG = true;
    protected static int MAX_RUN_TIMES = 6;

    public static void main(String[] args) throws Exception {
        //sleep 用法
//        Thread t1 = new T1();
//        t1.start();
//        Thread.sleep(10000);
//        System.out.println("主线程睡醒了");
//        RUN_FLAG=false;
        //join 用法
//        Thread t2 = new T2("子线程");
//        t2.start();
//        t2.join();
//        t2.run();
//        for(int i = 0; i< TreadTest.MAX_RUN_TIMES; i++) {
//            System.out.println( "主线程运行第" + i + "次");
//        }
        //yield用法
//        T3 t33 = new T3("IIIIIIII");
//        T3 t34 = new T3("OOOOOOOO");
//        for(int i = 0; i< TreadTest.MAX_RUN_TIMES; i++) {
//            System.out.println( "主线程运行第" + i + "次");
//        }
//        t33.start();
//        t34.start();
        //线程优先级
//        Thread t21 = new T4("低优先级线程");
//        Thread t22 = new T4("高优先级线程");
//
//        t21.setPriority(Thread.MIN_PRIORITY);
//        t22.setPriority(Thread.MAX_PRIORITY);
//
//        t21.start();
//        t22.start();
        //线程同步
//      final  Timer  timer = new Timer();
//        Thread t1 = new Thread(new Runnable() {
//            public void run() {
//                timer.doSomething("T1");
//            }
//        });
//        Thread t2 = new Thread(new Runnable() {
//            public void run() {
//                timer.doSomething("T2");
//            }
//        });
//        t1.start();t2.start();
        //线程死锁
//        final Object obj1 = new Object();
//        final Object obj2 = new Object();
//        new Thread(new Runnable() {
//            public void run() {
//                synchronized (obj1){
//                    System.out.println("线程1获得了obj1");
//                    System.out.println("线程1尝试获取获取obj2");
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    synchronized (obj2){
//                        System.out.println("线程1获得了obj2");
//                    }
//
//                }
//            }
//        }).start();
//        new Thread(new Runnable() {
//            public void run() {
//                synchronized (obj2){
//                    System.out.println("线程2获得了obj2");
//                    System.out.println("线程2尝试获取获取obj1");
//                    try {
//                        Thread.sleep(1900);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    synchronized (obj1){
//                        System.out.println("线程2获得了obj1");
//                    }
//
//                }
//            }
//        }).start();

    }
}


class T1 extends Thread {
    @Override
    public void run() {
        while (true) {
            if (!TreadTest.RUN_FLAG) {
                break;
            }
            System.out.println(new Date());
        }
    }
}

class T2 extends Thread {
    public T2(String s) {
        super(s);
    }

    @Override
    public void run() {
        for (int i = 0; i < TreadTest.MAX_RUN_TIMES; i++) {
            System.out.println(this.getName() + "运行第" + i + "次");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class T3 extends Thread {
    public T3(String s) {
        super(s);
    }

    @Override
    public void run() {
        for (int i = 0; i < 2 * TreadTest.MAX_RUN_TIMES; i++) {
            System.out.println(this.getName() + "  " + i);
            if (i % 4 == 0) {
                yield();//他妈的随机的
            }
        }
    }
}

class T4 extends Thread {
    public T4(String s) {
        super(s);
    }

    @Override
    public void run() {
        for (int i = 0; i < TreadTest.MAX_RUN_TIMES; i++) {

            System.out.println(this.getName() + "运行第" + i + "次");
        }
    }
}

class Timer {
    private static int num = 0;

    void doSomething(String name) {
        synchronized (this) {
            num++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "：你是第" + num + "个使用timer的线程");
        }
    }
}
