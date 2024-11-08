package juc.concurrent2;

import java.time.LocalTime;

/**
 * @desc
 * @class ThreadDaemon
 * @since 2022-06-01
 */
public class Thread_3_Daemon {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new TimerThread();
        t.setDaemon(true);
        t.start();
        Thread.sleep(1100);
    }

}

class TimerThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println(LocalTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
