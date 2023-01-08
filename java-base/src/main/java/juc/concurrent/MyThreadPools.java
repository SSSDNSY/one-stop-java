package juc.concurrent;

import java.util.Date;
import java.util.LinkedList;

/**
 * @description:
 * @author: pengzh
 * @createDate: 2019/6/9$ 21:53$
 */
public class MyThreadPools {

    int poolSize;
    LinkedList<Runnable> taskLists = new LinkedList<Runnable>();


    public MyThreadPools() {
        poolSize = 10;
        for (int i = 0; i < poolSize; i++) {
            new ThreadPools("任务线程" + i).start();
        }
    }

    public void add(Runnable task) {
        synchronized (taskLists) {
            taskLists.addLast(task);
            taskLists.notifyAll();
        }
    }

    class ThreadPools extends Thread {

        public ThreadPools(String name) {
            super(name);
        }

        Runnable task;

        @Override
        public void run() {
            System.out.println("启动" + this.getName());
            if(System.currentTimeMillis()/1000%5==0){
                System.out.println("线程池等待中");
            }
            while (true) {
                synchronized (taskLists) {
                    while (taskLists.isEmpty()) {
                        try {
                            taskLists.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 允许添加任务的线程可以继续添加任务
                    task = taskLists.removeLast();
                    taskLists.notifyAll();
                }
                task.run();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadPools mp = new MyThreadPools();
        for (int i = 0; i < 500; i++) {
            Runnable task = new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "  " + new Date() + " 任务已执行！");
                }
            };
            Thread.sleep(100);
            mp.add(task);
        }

    }

}

