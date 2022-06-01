package lang.cjuc.concurrent2;

import org.junit.Test;

/**
 * @author pengzh
 * @desc 如果线程需要执行一个长时间任务，就可能需要能中断线程。
 * 中断线程就是其他线程给该线程发一个信号，该线程收到信号后结束执行run()方法，使得自身线程能立刻结束运行。
 * <p>
 * 我们举个栗子：假设从网络下载一个100M的文件，如果网速很慢，用户等得不耐烦，
 * 就可能在下载过程中点“取消”，这时，程序就需要中断下载线程的执行。
 * 中断一个线程非常简单，只需要在其他线程中对目标线程调用interrupt()方法，
 * 目标线程需要反复检测自身状态是否是interrupted状态，如果是，就立刻结束运行。
 * @class ThreadInterrupt
 * @since 2022-06-01
 */
public class Thread_2_Interrupt {

    @Test
    public void TestInterrupt() throws Exception {
        Thread t = new MyThread();
        t.start();
        Thread.sleep(1);
        t.interrupt();
        t.join();
        System.out.println("main end");
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (!interrupted()) {
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(++i);
            }
        }
    }

    /**
     注意到HelloThread的标志位boolean running是一个线程间共享的变量。
     线程间共享变量需要使用volatile关键字标记，确保每个线程都能读取到更新后的变量值。
     为什么要对线程间共享的变量用关键字volatile声明？这涉及到Java的内存模型。在Java虚拟机中，
     变量的值保存在主内存中，但是，当线程访问变量时，它会先获取一个副本，并保存在自己的工作内存中。
     如果线程修改了变量的值，虚拟机会在某个时刻把修改后的值回写到主内存，但是，这个时间是不确定的！

     ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
     Main Memory
     │                               │
     ┌───────┐┌───────┐┌───────┐
     │  │ var A ││ var B ││ var C │  │
     └───────┘└───────┘└───────┘
     │     │ ▲               │ ▲     │
     ─ ─ ─│─│─ ─ ─ ─ ─ ─ ─ ─│─│─ ─ ─
     │ │               │ │
     ┌ ─ ─ ┼ ┼ ─ ─ ┐   ┌ ─ ─ ┼ ┼ ─ ─ ┐
     ▼ │               ▼ │
     │  ┌───────┐  │   │  ┌───────┐  │
     │ var A │         │ var C │
     │  └───────┘  │   │  └───────┘  │
     Thread 1          Thread 2
     └ ─ ─ ─ ─ ─ ─ ┘   └ ─ ─ ─ ─ ─ ─ ┘
     */
    public static void main(String[] args) throws InterruptedException {
        HelloThread t = new HelloThread();
        t.start();
        Thread.sleep(1);
        t.running = false; // 标志位置为false
    }

}

class HelloThread extends Thread {
    public volatile boolean running = true;

    public void run() {
        int n = 0;
        while (running) {
            n++;
            System.out.println(n + " hello!");
        }
        System.out.println("end!");
    }
}
