package juc.concurrent.disrupter.base;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class LongEventProducer {
    private RingBuffer<LongEvent> buffer;

    public LongEventProducer(RingBuffer<LongEvent> buffer) {
        this.buffer = buffer;
    }

    public void onData(ByteBuffer bb) {
        long sequence =  buffer.next();
        try {
            LongEvent event = buffer.get(sequence);
            event.setValue(bb.getLong(0));
        } catch (Exception e) {

        } finally {
            buffer.publish(sequence);
        }
    }
}
