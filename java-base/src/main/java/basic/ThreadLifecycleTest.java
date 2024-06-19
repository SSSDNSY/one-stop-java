package basic;

/**
 * 线程生命周期
 *
 * @class ThreadLifecycleTest
 * @since 2024-06-19
 */
public class ThreadLifecycleTest {

    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        interrupterTest();

    }

    private static void interrupterTest() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("启动线程");
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(System.currentTimeMillis());
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("中断线程");
                        break;
                    }
                }
            }
        };

        thread.start();
        Thread.sleep(10);

        thread.interrupt();
    }

}


/**
 * 并发问题
 */
class Main extends Thread {
    public static int amount = 0;

    public static void main(String[] args) {
        Main thread = new Main();
        thread.start();
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isAlive());
        System.out.println(amount);
        amount++;
        System.out.println(amount);
    }

    public void run() {
        amount++;
    }
}

/**
 * 使用alive处理并发冲突
 */
 class Main1 extends Thread {
    public static int amount = 0;

    public static void main(String[] args) {
        Main thread = new Main();
        thread.start();
        // Wait for the thread to finish
        while(thread.isAlive()) {
            System.out.println("Waiting...");
        }
        // Update amount and print its value
        System.out.println("Main: " + amount);
        amount++;
        System.out.println("Main: " + amount);
    }
    public void run() {
        amount++;
    }
}




/**
 * 在这个例子中，worker 线程在执行任务时会定期检查中断状态。
 * 如果检测到中断请求，它会捕获 InterruptedException 并退出循环，从而停止执行。
 * 使用 interrupt() 方法时，应该确保线程能够正确响应中断请求，避免线程在被中断后继续执行，这可能会导致资源泄露或其他问题。
 */
class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // 执行任务
                System.out.println("执行任务...");
                try {
                    Thread.sleep(1000); // 模拟耗时操作
                } catch (InterruptedException e) {
                    // 如果线程被中断，捕获异常并退出循环
                    System.out.println("线程被中断，退出任务执行。");
                    break;
                }
            }
        });

        worker.start();
        // Thread.sleep(1000);
        // 模拟中断线程
        worker.interrupt();
    }
}