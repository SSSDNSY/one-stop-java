package juc.concurrent;

/**
 * synchronized 锁重入
 */
public class ConcurrentDemoSynchronized6 {
    static class Sup {
        public int i = 10;

        public synchronized void setSupI() {
            i--;
            System.out.println("prarent i="+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Sub extends Sup {
        public synchronized void setSubI() {
            while (i > 0) {
                i--;
                System.out.println("sub i=" + i);
                this.setSupI();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Sub b = new Sub();
                b.setSubI();
            }
        });
        t1.start();
    }
}
