package cjuc.concurrent.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    static LinkedBlockingQueue q = new LinkedBlockingQueue();


    public static void main(String[] args) {
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        Consumer c3 = new Consumer(q);

        Provider p1 = new Provider(q);
        Provider p2 = new Provider(q);
        Provider p3 = new Provider(q);
        Provider p4 = new Provider(q);
        Provider p5 = new Provider(q);

        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(c1);
        es.execute(c2);
        es.execute(c3);

        es.execute(p1);
        es.execute(p2);
        es.execute(p3);
        es.execute(p4);
        es.execute(p5);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p1.stop();
        p2.stop();
        p3.stop();
        p4.stop();
        p5.stop();
    }
}
