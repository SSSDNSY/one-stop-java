package sssdnsy.cjuc.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS cpu级别原子比较换值 Java是dowhile实现 但是Java存在一个ABA问题 就是前后状态一致但是中间值不一致问题。
 *
 * @author imi
 */
public class ABAQuestionOfCAS {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        //ABA问题复现
        new Thread(() -> {
            atomicReference.compareAndSet(100, 1);
            System.out.println(Thread.currentThread().getName() + " " + atomicReference.get());
            atomicReference.compareAndSet(1, 100);
            System.out.println(Thread.currentThread().getName() + " " + atomicReference.get());
        }, "t1").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + atomicReference.get());
            atomicReference.compareAndSet(100, 200);
            System.out.println(Thread.currentThread().getName() + " " + atomicReference.get());
        }, "t2").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //ABA问题解决 加时间戳 乐观锁
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            asr.compareAndSet(100, 1, asr.getStamp(), asr.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " " + asr.getStamp() + " " + asr.getStamp());
            asr.compareAndSet(1, 100, asr.getStamp(), asr.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " " + asr.getStamp() + " " + asr.getStamp());
        }, "t3").start();
        new Thread(() -> {
            int s  = asr.getStamp();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "修改与否： " + asr.compareAndSet(100, 1,s , asr.getStamp() + 1)+" "  + asr.getStamp());
            System.out.println(Thread.currentThread().getName() + " 最终值" + asr.getReference());
        }, "t4").start();

    }
}
