package juc.concurrent.concurrentUtils;

import java.util.concurrent.*;

/**
 * @Description: 类似ajax 异步请求处理

 * @date: 2019/9/14
 */
public class UseFutrue implements Callable<String> {
    private String param;

    public UseFutrue(String param) {
        this.param = param;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        String rlt = this.param + "处理完成！";
        return rlt;
    }

    public static void main(String[] args) throws Exception {
        String qryStr = "任务1 ";
        FutureTask futureTask = new FutureTask(new UseFutrue(qryStr));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future f = executorService.submit(futureTask);
        String rlt = (String) futureTask.get();
        System.out.println(rlt);
        while (true) {
            if (f.get() == null) {
                System.out.println("Future执行完毕！");
                break;
            }
        }
        executorService.shutdown();

    }
}
