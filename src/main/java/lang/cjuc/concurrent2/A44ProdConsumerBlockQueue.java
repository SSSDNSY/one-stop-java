package lang.cjuc.concurrent2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/1 10:21
 * @description ：volatile 、CAS、 atomic 、blockqueue 线程交互 、原子引用 的结合例子
 * @modified By：
 */
public class A44ProdConsumerBlockQueue {
    public static void main(String[] args) {
        ResData rd = new ResData(new ArrayBlockingQueue<>(1));
        new Thread(()->{
            try {
                rd.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产者 ").start();

        new Thread(()->{
            try {
                rd.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"消费者 ").start();

        //线程休眠5s
        try{TimeUnit.SECONDS.sleep(5);}catch(Exception e){e.printStackTrace();}
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("》》》主线程叫停所有活动");
        rd.stop();
    }
}
/**
*@Description： 资源类
*@Author: pengzh
*@date: 2020/2/1
*/
class ResData{
    BlockingQueue<String> bq = null;
    private volatile boolean WORK_FLAG = true;
    private AtomicInteger ai = new AtomicInteger();
    public ResData(BlockingQueue<String> bq){
        this.bq = bq;
    }

    public void product() throws InterruptedException {
        String data = null;
        boolean retVal ;
        while (WORK_FLAG){
            data = ai.incrementAndGet()+"";
            retVal = bq.offer(data,2L, TimeUnit.SECONDS);
            if(retVal){
                System.out.println(Thread.currentThread().getName()+"\t"+"插入阻塞队列成功:"+data+"，当前队列长度"+bq.size());
            }else {
                System.out.println(Thread.currentThread().getName()+"\t"+"插入阻塞队列失败！"+data);
            }
            //线程休眠一会
            try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){e.printStackTrace();}

        }
        System.out.println("flag="+WORK_FLAG+",生产结束！");

    }
    public void consumer() throws InterruptedException {
        while(WORK_FLAG){
            String retData = bq.poll(2L,TimeUnit.SECONDS);
            if(retData==null||retData.equalsIgnoreCase("")){
                WORK_FLAG=false;
                System.out.println(Thread.currentThread().getName()+"\t超过2s没有取到值！");
            }
            //线程休眠一会
            try{TimeUnit.SECONDS.sleep(2);}catch(Exception e){e.printStackTrace();}
            
            System.out.println(Thread.currentThread().getName()+"\t"+"获取到的值："+retData+"，当前队列长度"+bq.size());
        }
        System.out.println("flag="+WORK_FLAG+",消费结束！");
    }

    public void stop() {
        this.WORK_FLAG = false;
    }
}
