package juc.concurrent;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 */
public class ConcurrentDemoAtomicUse {

    private static AtomicInteger ac = new AtomicInteger(0);
    //当多个addAndGet在一个方法里面是非原子性的 ，必须加synchronized
      int mutilAdd(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ac.addAndGet(1);
        ac.addAndGet(2);
        ac.addAndGet(3);
        ac.addAndGet(4);
        return ac.get();
    }

    public static void main(String[] args) {
        ConcurrentDemoAtomicUse cda = new ConcurrentDemoAtomicUse();
        ArrayList<Thread> ats = new ArrayList<Thread>();
        for(int i =0;i<100;i++){
            ats.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(cda.mutilAdd());
                }
            }));
        }

        for (Thread t1: ats) {
            t1.start();
        }
    }
}
