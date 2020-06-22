package lang.cjuc.concurrent;

public class ConcurrentDemoSynchronized extends Thread{
    int i =  5;
    @Override
    public synchronized void run() {
        i--;
        System.out.println("i="+i);
    }

    public static void main(String[] args) {
        /**
         * synchnized 关键字：保证一个CPU时刻只有一个线程能获得对象锁
         * 锁竞争：当一个线程获得对象锁之后，其他线程一直不断在竞争该对象的锁，
         *        如果竞争的线程数量达到一定的级别之后，就会导致高CPU占用率。
         */
        ConcurrentDemoSynchronized c = new ConcurrentDemoSynchronized();

        Thread t1 = new Thread(c,"t1");
        Thread t2 = new Thread(c,"t2");
        Thread t3 = new Thread(c,"t3");
        Thread t4 = new Thread(c,"t4");
        Thread t5 = new Thread(c,"t5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }


}
