package juc.concurrent2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @since 2020-07-02
 */
public class SynchronizedDemo {


    public static void main(String[] args) {
//        final SynchArea synch = new SynchArea();
//        final SynchArea synch2 = new SynchArea();
//        final SynchMethod synch = new SynchMethod();
//        final SynchMethod synch2 = new SynchMethod();
        final SynchClass synch = new SynchClass();
        final SynchClass synch2 = new SynchClass();

        final ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() -> {
            for (int i = 0; i < 5; i++) {
                synch.synch();
            }
        });
        executorService.execute(() -> {
            for (int i = 0; i < 5; i++) {
                synch2.synch();
            }
        });
        executorService.execute(() -> {
            for (int i = 0; i < 5; i++) {
                synch.synch();
            }
        });
        executorService.shutdown();
    }
}

//同步代码块 作用于一个对象
class SynchArea {
    private int m;

    void synch() {
        synchronized (this) {
            System.out.print(" " + m++);
        }
    }
}

//同步方法 作用于一个对象
class SynchMethod {
    int m;

    synchronized void synch() {
        System.out.print(" " + m++);
    }
}

//同步整个类 同一个类的不同对象上
class SynchClass {
    private int m;

    void synch() {
        synchronized (SynchClass.class) {
            System.out.print(" " + m++);
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo.main(args);
    }
}
