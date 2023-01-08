package juc.concurrent.disrupter.base;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 主函数
 * @Author: pengzh
 * @date: 2019/9/16
 */
public class LongEventMain {
    static final int MAX_VALUE = 100000;
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        LongEventFactory factory = new LongEventFactory();
        int ringBuffer = 1024 * 1024;

        Disruptor<LongEvent> disruptor = new Disruptor(factory, ringBuffer, es, ProducerType.SINGLE, new YieldingWaitStrategy());

        //消费事件  观察者模式
        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();
        RingBuffer buffer = disruptor.getRingBuffer();
        LongEventProducer longEventProducer = new LongEventProducer(buffer);

        //提交事件两个过程
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long i = 0; i <MAX_VALUE; i++) {
            bb.putLong(0,i);
            longEventProducer.onData(bb);
        }
        disruptor.shutdown();
        es.shutdown();

    }
}
