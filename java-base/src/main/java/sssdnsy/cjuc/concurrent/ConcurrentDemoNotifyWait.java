package sssdnsy.cjuc.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ConcurrentDemoNotifyWait {

    final List<String> list = new ArrayList<>();
    final int i2 = 0;

    void add() {
        list.add("imi");
    }

    static Object o = new Object();

    int size() {
        return list.size();
    }

    public static void main(String[] args) {
        ConcurrentDemoNotifyWait cdnw = new ConcurrentDemoNotifyWait();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    for (int j = 0; j < 10; j++) {
                        try {
                            if(cdnw.size()<5){
                                cdnw.add();
                            }else {
                                wait();
                            }
                            System.out.println(" t1 cdnw.size=" + cdnw.size());
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    while (true) {
                        if (cdnw.size() == 5) {
                            System.out.println(" t2 发出通知i=" + cdnw.size());
                            notifyAll();
                            throw new RuntimeException();
                        }
                    }
                }
            }
        });

        t2.start();
        t1.start();
    }
}
