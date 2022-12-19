package sssdnsy.cjuc.concurrent;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/2 21:47
 * @description： 死锁
 * <p>
 * 两个线程指两个或两个以上的线程在执行过程中，因争抢资源而造成“相互等待”的现象。
 * 若无外力干涉无法推进下去，如果系统资源充足不易出现，但是争夺有限的资源容易陷入死锁。
 * @modified By：
 */

class DeadLockDemo implements Runnable {
    private String lockA;
    private String lockB;

    public DeadLockDemo(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t获得了锁" + lockA + "，尝试获得" + lockB);
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t获得了锁" + lockB + "，尝试获得" + lockA);
            }
        }
    }
}

public class A56DeadLock {
    public static void main(String[] args) {
        String lockA = "ALock";
        String lockB = "BLock";
        new Thread(new DeadLockDemo(lockA, lockB), "AAA线程").start();
        new Thread(new DeadLockDemo(lockB, lockA), "BBB线程").start();
    }
}
