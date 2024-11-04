package features.jdk21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 虚拟线程测试
 */
public class VirtualThreadTest {

    public static void main(String[] args) {

        Runnable runnable = () -> System.out.println("Inside Runnable");

        //1
        Thread.startVirtualThread(runnable);
        //2
        Thread virtualThread = Thread.ofVirtual().start(runnable);
        //3
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        executor.submit(runnable);
    }


}
