package sssdnsy.cjuc.concurrent.threadpools;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Runnable{

    LinkedBlockingQueue<Data> q ;
    public Consumer(LinkedBlockingQueue q) {
        this.q = q;
    }

    @Override
    public void run() {
        Random r = new Random();
        while(true){
            try {
                Thread.sleep(r.nextInt(1000));
                Data d =  q.take();
                System.out.println(Thread.currentThread().getName()+"消费了数据： "+d.getId()+","+d.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
