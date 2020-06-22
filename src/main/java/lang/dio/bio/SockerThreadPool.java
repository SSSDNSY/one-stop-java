package lang.dio.bio;

import java.util.concurrent.*;

public class SockerThreadPool {
    private ExecutorService executorService;
    public SockerThreadPool(int maxPoolSize, int queueSize) {
        this.executorService = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize)
        );
    }
    public void execute(Runnable task){
        this.executorService.execute(task);
    }
}
