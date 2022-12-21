package cjuc.concurrent;

public class ConcurrentDemoSynchronized2 {

    private static int num = 0;

    //static 加了就是类方法  对象锁  不加 就是对象方法
    private static synchronized void setNum(String tag) {
        try {
            if ("a".equals(tag)) {
                num = 'a';
                System.out.println("tab=" + tag);
            } else {
                num = 'b';
                System.out.println("tab=" + tag);
            }
            Thread.sleep(5000);
            System.out.println("num=" + num);

        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        final ConcurrentDemoSynchronized2 c1 = new ConcurrentDemoSynchronized2();
        final ConcurrentDemoSynchronized2 c2 = new ConcurrentDemoSynchronized2();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                c1.setNum("a");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                c2.setNum("b");
            }
        });
        t1.start();
        t2.start();

    }
}
