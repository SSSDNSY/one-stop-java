package sssdnsy.cjuc.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author ：pengzh
 * @date ：Created in 2020/1/31 10:27
 * @description：倒计时锁
 * @modified By：
 */
public class A32CountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(3));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + ",还剩" + countDownLatch.getCount() + "个国家");
            }, CountryEnum.foreachCountryEnum(i).getRetName() + "已经灭亡").start();
        }
        countDownLatch.await();
        System.out.println("秦国一统天下");
        System.out.println();
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getRetCode());
        System.out.println(CountryEnum.ONE.getRetName());
    }
}

enum CountryEnum {
    ONE(1, "齐"),
    TWO(2, "楚"),
    THREE(3, "燕"),
    FOUR(4, "赵"),
    FIVE(5, "魏"),
    SIX(6, "韩");
    private Integer retCode;
    private String retName;

    CountryEnum(Integer retCode, String retName) {
        this.retCode = retCode;
        this.retName = retName;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetName() {
        return retName;
    }

    public static CountryEnum foreachCountryEnum(int index) {
        CountryEnum[] arr = CountryEnum.values();
        for (CountryEnum e : arr) {
            if (index == e.getRetCode()) {
                return e;
            }
        }
        return null;
    }
}
