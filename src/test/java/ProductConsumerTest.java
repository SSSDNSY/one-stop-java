import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: imi
 * @Date: 2019/3/11 21:03
 * @Description:
 */
public class ProductConsumerTest {
    private  final int LEN = 5;
    private Queue<Integer> queue = new LinkedList();
    public static void main(String[] args) throws  Exception{
        ProductConsumerTest pt1  = new ProductConsumerTest();
        PorductorThread p1 = pt1.new PorductorThread();
        ConsumerThread c1 = pt1.new ConsumerThread();
        p1.start();c1.start();
        Thread.sleep(500);
        System.exit(0);
    }

    class PorductorThread extends  Thread{
        @Override
        public void run() {
            while (true) {
                synchronized (queue){
                    if(queue.size()==LEN){
                        try{
                            queue.wait();
                        }catch (InterruptedException e){
                        }
                    }else{
                    queue.add(1);
                    queue.notify();
                    System.out.println("生产者 "+queue.size());
                } }
            }
        }
    }

    class ConsumerThread extends  Thread{
        @Override
        public void run() {
            while (true) {
                synchronized (queue){
                    if(queue.size()== 0){
                        try{
                            queue.wait();
                        }catch (InterruptedException e){
                        }
                    }else{
                    queue.poll();
                    queue.notify();
                    System.out.println("消费者 "+queue.size());
                }}
            }
        }
    }
}

