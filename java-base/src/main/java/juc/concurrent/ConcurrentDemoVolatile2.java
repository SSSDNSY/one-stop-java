package juc.concurrent;


/**
 * volatile 可见性
 */
public class ConcurrentDemoVolatile2 {
    public static void main(String[] args) {
        final MyData md = new MyData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" 进入 ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                md.addNumber();
                System.out.println(Thread.currentThread().getName()+" 修改:"+md.number);

            }
        }).start();
        while (md.number==0){

        }
        System.out.println(Thread.currentThread().getName()+" md.number=:"+md.number);
    }
}

class MyData {
    /**
     * 体会可见性  volatile
     */
    volatile int  number = 0;

    void addNumber() {
        this.number = 666;
        this.number++;
    }
}
