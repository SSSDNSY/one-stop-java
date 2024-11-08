package juc.concurrent2;

import org.junit.Test;

/**
 * @desc 线程的状态
 * @class ThreadStatus
 * @since 2022-06-01
 * ┌─────────────┐
 * │     New     │
 * └─────────────┘
 * │
 * ▼
 * ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
 * ┌─────────────┐ ┌─────────────┐
 * ││  Runnable   │ │   Blocked   ││
 * └─────────────┘ └─────────────┘
 * │┌─────────────┐ ┌─────────────┐│
 * │   Waiting   │ │Timed Waiting│
 * │└─────────────┘ └─────────────┘│
 * ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
 * │
 * ▼
 * ┌─────────────┐
 * │ Terminated  │
 * └─────────────┘
 * <p>
 * 线程正常终止：run()方法执行到return语句返回；
 * 线程意外终止：run()方法因为未捕获的异常导致线程终止；
 * 对某个线程的Thread实例调用stop()方法强制终止（强烈不推荐使用）
 */
public class Thread_1_Status {


    @Test
    public void testJoin() throws Exception {
        Thread t = new Thread(() -> {
            System.out.println("hello");
        });
        System.out.println("start");
        t.start();
        t.join();
        System.out.println("end");
    }

}
