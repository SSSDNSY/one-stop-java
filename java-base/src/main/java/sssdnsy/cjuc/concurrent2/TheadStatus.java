package sssdnsy.cjuc.concurrent2;

/**
 * @author pengzh
 * @desc
 * @class TheadStatus
 * @since 2022-04-14
 */
public class TheadStatus {


    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "a");
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "b");

        //1
//        a.start();
//        b.start();
//        System.out.println(a.getName() + ":" + a.getState()); // 输出？
//        System.out.println(b.getName() + ":" + b.getState()); // 输出？


        //2
        a.start();
        Thread.sleep(500); // 需要注意这里main线程休眠了1000毫秒，而testMethod()里休眠了2000毫秒
        b.start();
        System.out.println(a.getName() + ":" + a.getState()); // 输出？
        System.out.println(b.getName() + ":" + b.getState()); // 输出？
    }


    // 同步方法争夺锁
    static synchronized void testMethod() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 1测试方法的main线程只保证了a，b两个线程调用start()方法（转化为RUNNABLE状态），如果CPU执行效率高一点，还没等两个线程真正开始争夺锁，就已经打印此时两个线程的状态（RUNNABLE）了。
 * 当然，如果CPU执行效率低一点，其中某个线程也是可能打印出BLOCKED状态的（此时两个线程已经开始争夺锁了）。
 *
 * 2
 * a的状态转换过程：RUNNABLE（a.start()） -> TIMED_WATING（Thread.sleep(1000)）->RUNABLE（sleep(1000)时间到）->BLOCKED(未抢到锁) -> TERMINATED
 * b的状态转换过程：RUNNABLE（b.start()) -> BLOCKED(未抢到锁) ->TERMINATED
 *
 */
