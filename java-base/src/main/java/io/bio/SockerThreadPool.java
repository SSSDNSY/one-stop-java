package io.bio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SockerThreadPool {
    private ExecutorService executorService;
    public SockerThreadPool(int maxPoolSize, int queueSize) {
        this.executorService = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueSize)
        );
    }
    public void execute(Runnable task){
        this.executorService.execute(task);
    }
}
