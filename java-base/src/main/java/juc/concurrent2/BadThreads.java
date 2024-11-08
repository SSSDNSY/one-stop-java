package juc.concurrent2;

/**
 * @class sssdnsy.cjuc.concurrent2.BadThreads
 * @desc
 * @since 2021-03-11
 */
public class BadThreads {

    static volatile String message;

    private static class CorrectorThread
            extends Thread {

        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
            // Key statement 1:
            message = "Mares do eat oats.";
        }
    }

    public static void main(String args[])
            throws InterruptedException {

        (new CorrectorThread()).start();
        message = "Mares do not eat oats.";
        Thread.sleep(1000);
        // Key statement 2:
        System.out.println(message);
    }
}
