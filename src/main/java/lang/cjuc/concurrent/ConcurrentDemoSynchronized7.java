package lang.cjuc.concurrent;

/**
 * synchronized 锁重入
 */
public class ConcurrentDemoSynchronized7 {

    int i =0;
    public synchronized void func1(){
        while (true){
            try {
                i++;
                System.out.println("i "+i);
                if(i %10 ==0){
                    Integer.parseInt("a");
                }
                Thread.sleep(100);
            } catch (Throwable e) {
                e.printStackTrace();
//                throw new RuntimeException();
                continue;
            }
        }
    }

    public static void main(String[] args) {
        ConcurrentDemoSynchronized7 c7  = new ConcurrentDemoSynchronized7();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                c7.func1();
            }
        });
        t1.start();
    }
}
