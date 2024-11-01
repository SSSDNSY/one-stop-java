package juc.concurrent.disrupter.base;

import com.lmax.disruptor.EventFactory;

/**
 * @Description: 事件工厂
 * @Author: pengzh
 * @date: 2019/9/16
 */
public class LongEventFactory implements EventFactory {

    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
