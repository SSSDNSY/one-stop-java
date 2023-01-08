package juc.concurrent.masterworker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {
    //任务集合
    private ConcurrentLinkedQueue<Task> workQueue;
    //任务结果
    private ConcurrentHashMap<String, Object> resultMap;

    public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true) {
            Task input = workQueue.poll();
            if (input != null) {
                Object output = handle(input);
                System.out.println(output + "，runing");
                resultMap.put(Integer.toString(input.getId()), output);
            } else {
                break;
            }
        }
    }

    private Object handle(Task input) {
        Object output = null;
        try {
            Thread.sleep(500);
            output = input.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }


}
