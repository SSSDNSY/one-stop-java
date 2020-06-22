package lang.cjuc.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 可见性 不保证原子性  禁止指令重排
 */
public class ConcurrentDemoVolatile1 extends  Thread{

//    private static volatile int count ;//volatile 可见性
    private static AtomicInteger atomicInteger = new AtomicInteger(0);//原子性
    public synchronized void addCount(){
       for(int i =0;i<1000;i++){
           atomicInteger.incrementAndGet();
       }
        System.out.println("count="+atomicInteger);
    }

    @Override
    public void run() {
        addCount();
    }

    public static void main(String[] args) {
        ConcurrentDemoVolatile1[] cdv1 = new ConcurrentDemoVolatile1[10];
        for(int i =0 ;i<10;i++){
            cdv1[i] = new ConcurrentDemoVolatile1();
        }
        for (int i = 0; i <10 ; i++) {
            cdv1[i].start();
        }
    }
}
