package sssdnsy.cjuc.concurrent;

/**
 * @Auther: imi
 * @Date: 2019/4/2 16:49
 * @Description:
 */
public class SynchronizeProcessTest {
    public static final long LOOP_TIME = 1000 * 10000;



    public static void main(String[] args) {
        Counter countingProcessor = new CounterImpl();
        runTask(countingProcessor);
    }

    private static void  runTask(Counter processor) {
        Thread thread1 = new Thread(new ProcessTask(processor, LOOP_TIME), "thread-1");
        Thread thread2 = new Thread(new ProcessTask(processor, LOOP_TIME), "thread-2");
        thread1.start();
        thread2.start();
        while(thread1.isAlive() || thread2.isAlive()) {}
    }
}
