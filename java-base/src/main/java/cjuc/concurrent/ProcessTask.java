package cjuc.concurrent;


/**
 * @Auther: imi
 * @Date: 2019/4/2 16:51
 * @Description:
 */
public class ProcessTask implements Runnable {

    private Counter countingProcessor;
    private long loopTime;

    public ProcessTask(Counter countingProcessor, long loopTime) {
        this.countingProcessor = countingProcessor;
        this.loopTime = loopTime;
    }

    public void run() {
        int i = 0;
        while (i < loopTime) {
            countingProcessor.doProcessor();
            i ++;
        }
        System.out.println("Finally, the count is {}"+countingProcessor.getCounter());
    }
}
