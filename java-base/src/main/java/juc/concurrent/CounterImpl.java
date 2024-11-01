package juc.concurrent;

/**
 * @Auther: imi
 * @Date: 2019/4/2 16:47
 * @Description:
 */
public class CounterImpl implements Counter {
    private long i = 0;

    public long getCounter() {
        return i;
    }

    public void doProcessor() {
        synchronized (this) {
            i++;
        }
    }
}
